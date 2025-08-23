package com.demo.poc.entrypoint.provinces.service;

import java.util.Comparator;

import com.demo.poc.entrypoint.departments.exceptions.NoSuchDepartmentException;
import com.demo.poc.entrypoint.provinces.helper.ProvinceCacheHelper;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProvinceServiceImpl implements ProvinceService {

  private final ProvinceCacheHelper provinceCacheHelper;

  @Override
  public Flux<ProvinceEntity> findByDepartmentId(String departmentId) {
    return provinceCacheHelper.findByDepartmentId(departmentId)
        .sort(Comparator.comparing(ProvinceEntity::getId))
        .switchIfEmpty(Mono.error(new NoSuchDepartmentException(departmentId)));
  }
}
