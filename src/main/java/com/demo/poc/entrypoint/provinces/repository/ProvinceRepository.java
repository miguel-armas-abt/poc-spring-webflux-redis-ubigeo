package com.demo.poc.entrypoint.provinces.repository;

import java.util.List;

import com.demo.poc.commons.core.serialization.JsonSerializer;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import com.demo.poc.commons.custom.enums.UbigeoType;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class ProvinceRepository {

  private final ApplicationProperties properties;
  private final JsonSerializer jsonSerializer;

  public Flux<ProvinceEntity> findByDepartmentId(String departmentId) {
    return this.findAll()
        .filter(province -> departmentId.equals(province.getDepartmentId()));
  }

  private Flux<ProvinceEntity> findAll() {
    String provincePath = properties.getFilePaths().get(UbigeoType.PROVINCES.getLabel());
    List<ProvinceEntity> departments = jsonSerializer.readListFromFile(provincePath, ProvinceEntity.class);
    return Flux.fromIterable(departments);
  }
}
