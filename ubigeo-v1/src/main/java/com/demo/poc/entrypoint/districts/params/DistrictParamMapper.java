package com.demo.poc.entrypoint.districts.params;

import java.util.Map;

import com.demo.poc.commons.core.validations.ParamMapper;

import org.springframework.stereotype.Component;

@Component
public class DistrictParamMapper implements ParamMapper {

  @Override
  public Object map(Map<String, String> params) {
    return DistrictParam.builder()
        .departmentId(params.get("departmentId"))
        .provinceId(params.get("provinceId"))
        .build();
  }

  @Override
  public boolean supports(Class<?> paramClass) {
    return DistrictParam.class.isAssignableFrom(paramClass);
  }
}
