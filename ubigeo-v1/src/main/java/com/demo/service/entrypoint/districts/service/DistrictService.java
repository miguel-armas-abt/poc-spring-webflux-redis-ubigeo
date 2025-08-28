package com.demo.service.entrypoint.districts.service;

import com.demo.service.entrypoint.districts.repository.entity.DistrictEntity;
import reactor.core.publisher.Flux;

public interface DistrictService {

  Flux<DistrictEntity> findByProvinceIdAndDepartmentId(String provinceId, String departmentId);

}
