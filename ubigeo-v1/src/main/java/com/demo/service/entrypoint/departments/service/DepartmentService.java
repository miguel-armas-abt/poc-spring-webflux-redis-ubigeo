package com.demo.service.entrypoint.departments.service;

import com.demo.service.entrypoint.departments.repository.entity.DepartmentEntity;
import reactor.core.publisher.Flux;

public interface DepartmentService {

  Flux<DepartmentEntity> findAllDepartments();

}
