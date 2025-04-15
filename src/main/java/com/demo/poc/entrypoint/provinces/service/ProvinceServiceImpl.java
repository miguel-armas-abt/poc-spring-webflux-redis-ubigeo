package com.demo.poc.entrypoint.provinces.service;

import java.util.Comparator;

import com.demo.poc.entrypoint.provinces.helper.ProvinceCacheHelper;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProvinceServiceImpl implements ProvinceService {

  private final ProvinceCacheHelper provinceCacheHelper;

  @Override
  public Flux<ProvinceEntity> findProvincesByDepartmentId(String departmentId) {
    return provinceCacheHelper.findProvincesByDepartmentId(departmentId)
        .sort(Comparator.comparing(ProvinceEntity::getId));
  }
}
