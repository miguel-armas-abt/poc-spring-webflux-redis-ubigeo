package com.demo.service.entrypoint.departments.service;

import java.util.Comparator;

import com.demo.service.entrypoint.departments.helper.DepartmentCacheHelper;
import com.demo.service.entrypoint.departments.repository.entity.DepartmentEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentCacheHelper departmentCacheHelper;

  @Override
  public Flux<DepartmentEntity> findAllDepartments() {
    return departmentCacheHelper.findAllDepartments()
        .sort(Comparator.comparing(DepartmentEntity::getId));
  }
}