package com.demo.poc.entrypoint.ubigeo.dto.response;

import com.demo.poc.entrypoint.departments.repository.entity.DepartmentEntity;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UbigeoResponseDto implements Serializable {

  private String code;
  private DepartmentEntity department;
  private ProvinceEntity province;
  private DistrictEntity district;
}
