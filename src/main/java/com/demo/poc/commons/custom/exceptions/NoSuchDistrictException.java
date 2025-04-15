package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NoSuchDistrictException extends GenericException {

  public NoSuchDistrictException(String districtId) {
    super("districtId: " + districtId, ErrorDictionary.parse(NoSuchDistrictException.class));
  }

  public NoSuchDistrictException(String provinceId, String departmentId) {
    super("districtId: " + provinceId + ", departmentId: " + departmentId, ErrorDictionary.parse(NoSuchDistrictException.class));
  }
}
