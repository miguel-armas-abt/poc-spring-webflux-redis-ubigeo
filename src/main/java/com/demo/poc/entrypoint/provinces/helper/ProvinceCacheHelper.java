package com.demo.poc.entrypoint.provinces.helper;

import com.demo.poc.commons.custom.cache.RedisManager;
import com.demo.poc.commons.custom.constants.SymbolConstants;
import com.demo.poc.commons.custom.enums.UbigeoType;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import com.demo.poc.entrypoint.provinces.repository.ProvinceRepository;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProvinceCacheHelper {

  private final ApplicationProperties properties;
  private final ReactiveRedisTemplate<String, Object> redisTemplate;
  private final RedisManager redisManager;
  private final ProvinceRepository provinceRepository;

  public Flux<ProvinceEntity> findProvincesByDepartmentId(String departmentId) {
    return redisManager.isRedisAvailable()
        .filter(isRedisAvailable -> isRedisAvailable)
        .flatMapMany(isRedisAvailable -> getProvincesFromCacheIfPresent(departmentId))
        .switchIfEmpty(provinceRepository.findByDepartmentId(departmentId));
  }

  private Flux<ProvinceEntity> getProvincesFromCacheIfPresent(String departmentId) {
    String cacheKey = buildCacheKey(departmentId);
    return redisTemplate.opsForSet()
        .scan(cacheKey)
        .cast(ProvinceEntity.class)
        .switchIfEmpty(Flux.defer(() -> provinceRepository.findByDepartmentId(departmentId)
            .flatMap(province -> redisTemplate.opsForSet().add(cacheKey, province)
                .thenReturn(province))));
  }

  private String buildCacheKey(String departmentId) {
    return properties.searchCache(UbigeoType.PROVINCES.getLabel()).getKeyPrefix() + SymbolConstants.COLON + departmentId;
  }
}
