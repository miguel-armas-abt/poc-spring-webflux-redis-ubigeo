package com.demo.poc.entrypoint.provinces.service;

import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import reactor.core.publisher.Flux;

public interface ProvinceService {

  Flux<ProvinceEntity> findProvincesByDepartmentId(String departmentId);

}
