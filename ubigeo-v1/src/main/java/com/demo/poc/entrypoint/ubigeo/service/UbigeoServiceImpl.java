package com.demo.poc.entrypoint.ubigeo.service;

import com.demo.poc.entrypoint.departments.exceptions.NoSuchDepartmentException;
import com.demo.poc.entrypoint.districts.exceptions.NoSuchDistrictException;
import com.demo.poc.entrypoint.provinces.exceptions.NoSuchProvinceException;
import com.demo.poc.entrypoint.departments.helper.DepartmentCacheHelper;
import com.demo.poc.entrypoint.departments.repository.entity.DepartmentEntity;
import com.demo.poc.entrypoint.districts.helper.DistrictCacheHelper;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import com.demo.poc.entrypoint.provinces.helper.ProvinceCacheHelper;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import com.demo.poc.entrypoint.ubigeo.dto.response.UbigeoResponseDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UbigeoServiceImpl implements UbigeoService {

  private final DepartmentCacheHelper departmentCacheHelper;
  private final ProvinceCacheHelper provinceCacheHelper;
  private final DistrictCacheHelper districtCacheHelper;

  @Override
  public Mono<UbigeoResponseDto> findUbigeo(String ubigeoCode) {
    String departmentId = ubigeoCode.substring(0,2);
    String provinceId = ubigeoCode.substring(2,4);
    String districtId = ubigeoCode.substring(4,6);

    Mono<DepartmentEntity> selectedDepartment = departmentCacheHelper.findAllDepartments()
        .filter(department -> departmentId.equals(department.getId()))
        .singleOrEmpty()
        .switchIfEmpty(Mono.error(new NoSuchDepartmentException(departmentId)));

    Mono<ProvinceEntity> selectedProvince = provinceCacheHelper.findByDepartmentId(departmentId)
        .filter(province -> provinceId.equals(province.getId()))
        .singleOrEmpty()
        .switchIfEmpty(Mono.error(new NoSuchProvinceException(departmentId)));

    Mono<DistrictEntity> selectedDistrict = districtCacheHelper.findByProvinceIdAndDepartmentId(provinceId, departmentId)
        .filter(district -> districtId.equals(district.getId()))
        .singleOrEmpty()
        .switchIfEmpty(Mono.error(new NoSuchDistrictException(departmentId)));

    return Mono.zip(selectedDepartment, selectedProvince, selectedDistrict)
        .map(tuple -> UbigeoResponseDto.builder()
            .code(ubigeoCode)
            .department(tuple.getT1())
            .province(tuple.getT2())
            .district(tuple.getT3())
            .build()
        );
  }
}
