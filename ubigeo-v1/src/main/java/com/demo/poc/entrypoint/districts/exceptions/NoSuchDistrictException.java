package com.demo.poc.entrypoint.districts.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NoSuchDistrictException extends GenericException {

  private static final String EXCEPTION_CODE = "10.01.11";

  public NoSuchDistrictException(String districtId) {
    super(
        EXCEPTION_CODE,
        "No such district: " + districtId,
        BAD_REQUEST
    );
  }

  public NoSuchDistrictException(String provinceId, String departmentId) {
    super(
        EXCEPTION_CODE,
        "No such district in [provinceId: " + provinceId + "], [departmentId: " + departmentId + "]",
        BAD_REQUEST
    );
  }
}
