package com.demo.poc.entrypoint.districts.helper;

import com.demo.poc.commons.custom.cache.RedisManager;
import com.demo.poc.commons.custom.constants.SymbolConstants;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import com.demo.poc.entrypoint.districts.repository.DistrictRepository;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistrictCacheHelper {

  private static final String CACHE_NAME = "districts";

  private final ApplicationProperties properties;
  private final ReactiveRedisTemplate<String, Object> redisTemplate;
  private final RedisManager redisManager;
  private final DistrictRepository districtRepository;

  public Flux<DistrictEntity> findByProvinceIdAndDepartmentId(String provinceId, String departmentId) {
    return redisManager.isRedisAvailable()
        .filter(isRedisAvailable -> isRedisAvailable)
        .flatMapMany(isRedisAvailable -> getDistrictsFromCacheIfPresent(provinceId, departmentId))
        .switchIfEmpty(districtRepository.findByProvinceIdAndDepartmentId(provinceId, departmentId));
  }

  private Flux<DistrictEntity> getDistrictsFromCacheIfPresent(String provinceId, String departmentId) {
    String cacheKey = buildCacheKey(provinceId, departmentId);
    return redisTemplate.opsForSet()
        .scan(cacheKey)
        .cast(DistrictEntity.class)
        .switchIfEmpty(Flux.defer(() -> districtRepository.findByProvinceIdAndDepartmentId(provinceId, departmentId)
            .flatMap(district -> redisTemplate.opsForSet().add(cacheKey, district)
                .thenReturn(district))));
  }

  private String buildCacheKey(String provinceId, String departmentId) {
    return properties.searchCache(CACHE_NAME).getKeyPrefix()
        + SymbolConstants.COLON + provinceId
        + SymbolConstants.COLON + departmentId;
  }
}
