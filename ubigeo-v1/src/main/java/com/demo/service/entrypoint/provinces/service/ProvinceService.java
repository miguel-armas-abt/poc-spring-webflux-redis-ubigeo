package com.demo.service.entrypoint.provinces.service;

import com.demo.service.entrypoint.provinces.repository.entity.ProvinceEntity;
import reactor.core.publisher.Flux;

public interface ProvinceService {

  Flux<ProvinceEntity> findByDepartmentId(String departmentId);

}
