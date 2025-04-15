package com.demo.poc.entrypoint.districts.service;

import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import reactor.core.publisher.Flux;

public interface DistrictService {

  Flux<DistrictEntity> findByProvinceIdAndDepartmentId(String provinceId, String departmentId);

}
