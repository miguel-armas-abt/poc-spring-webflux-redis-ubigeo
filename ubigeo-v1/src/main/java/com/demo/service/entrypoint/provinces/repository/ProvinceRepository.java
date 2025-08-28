package com.demo.service.entrypoint.provinces.repository;

import com.demo.commons.serialization.JsonSerializer;
import com.demo.service.commons.properties.ApplicationProperties;
import com.demo.service.commons.enums.UbigeoType;
import com.demo.service.entrypoint.provinces.repository.entity.ProvinceEntity;
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
    return jsonSerializer.readListFromFile(provincePath, ProvinceEntity.class);
  }
}
