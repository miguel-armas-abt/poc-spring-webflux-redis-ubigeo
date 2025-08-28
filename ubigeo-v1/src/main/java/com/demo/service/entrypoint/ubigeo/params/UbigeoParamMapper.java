package com.demo.service.entrypoint.ubigeo.params;

import java.util.Map;

import com.demo.commons.validations.ParamMapper;

public class UbigeoParamMapper implements ParamMapper {

  @Override
  public Object map(Map<String, String> params) {
    return UbigeoParam.builder()
        .departmentId(params.get("departmentId"))
        .provinceId(params.get("provinceId"))
        .districtId(params.get("districtId"))
        .build();
  }

  @Override
  public boolean supports(Class<?> paramClass) {
    return UbigeoParam.class.isAssignableFrom(paramClass);
  }
}
