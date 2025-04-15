package com.demo.poc.entrypoint.departments.service;

import com.demo.poc.entrypoint.departments.repository.entity.DepartmentEntity;
import reactor.core.publisher.Flux;

public interface DepartmentService {

  Flux<DepartmentEntity> findAllDepartments();

}
