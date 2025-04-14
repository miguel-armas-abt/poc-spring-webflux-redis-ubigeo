package com.demo.poc.entrypoint.departments.service;

import java.util.Comparator;

import com.demo.poc.entrypoint.departments.helper.DepartmentCacheHelper;
import com.demo.poc.entrypoint.departments.repository.entity.DepartmentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Service;

@Slf4j
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