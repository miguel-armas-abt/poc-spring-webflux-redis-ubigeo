package com.demo.poc.entrypoint.departments.repository;


import java.util.Comparator;
import java.util.List;

import com.demo.poc.commons.core.serialization.JsonSerializer;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import com.demo.poc.commons.custom.enums.UbigeoType;
import com.demo.poc.entrypoint.departments.repository.entity.DepartmentEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

  private final ApplicationProperties properties;
  private final JsonSerializer jsonSerializer;

  public Flux<DepartmentEntity> findAll() {
    String filePath = properties.getFilePaths().get(UbigeoType.DEPARTMENTS.getLabel());
    List<DepartmentEntity> departments = jsonSerializer.readListFromFile(filePath, DepartmentEntity.class);
    return Flux.fromIterable(departments);
  }
}
