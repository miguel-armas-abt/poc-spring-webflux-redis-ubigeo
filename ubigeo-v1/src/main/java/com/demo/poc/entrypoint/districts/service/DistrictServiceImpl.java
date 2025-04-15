package com.demo.poc.entrypoint.districts.service;

import java.util.Comparator;

import com.demo.poc.commons.custom.exceptions.NoSuchDistrictException;
import com.demo.poc.entrypoint.districts.helper.DistrictCacheHelper;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DistrictServiceImpl implements DistrictService {

  private final DistrictCacheHelper districtCacheHelper;

  @Override
  public Flux<DistrictEntity> findByProvinceIdAndDepartmentId(String provinceId, String departmentId) {
    return districtCacheHelper.findByProvinceIdAndDepartmentId(provinceId, departmentId)
        .sort(Comparator.comparing(DistrictEntity::getId))
        .switchIfEmpty(Mono.error(new NoSuchDistrictException(provinceId, departmentId)));
  }
}
