package com.demo.service.entrypoint.provinces.helper;

import com.demo.service.commons.cache.RedisManager;
import com.demo.commons.constants.Symbol;
import com.demo.service.commons.enums.UbigeoType;
import com.demo.service.commons.properties.ApplicationProperties;
import com.demo.service.entrypoint.provinces.repository.ProvinceRepository;
import com.demo.service.entrypoint.provinces.repository.entity.ProvinceEntity;
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

  public Flux<ProvinceEntity> findByDepartmentId(String departmentId) {
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
    return properties.searchCache(UbigeoType.PROVINCES.getLabel()).getKeyPrefix() + Symbol.COLON + departmentId;
  }
}
