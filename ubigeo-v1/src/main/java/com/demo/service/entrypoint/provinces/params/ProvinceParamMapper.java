package com.demo.service.entrypoint.provinces.params;

import java.util.Map;

import com.demo.commons.validations.ParamMapper;

import org.springframework.stereotype.Component;

@Component
public class ProvinceParamMapper implements ParamMapper {

  @Override
  public Object map(Map<String, String> params) {
    return ProvinceParam.builder()
        .departmentId(params.get("departmentId"))
        .build();
  }

  @Override
  public boolean supports(Class<?> paramClass) {
    return ProvinceParam.class.isAssignableFrom(paramClass);
  }
}
