package com.demo.service.entrypoint.provinces.exceptions;

import com.demo.commons.errors.exceptions.GenericException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NoSuchProvinceException extends GenericException {

  private static final String EXCEPTION_CODE = "10.01.10";

  public NoSuchProvinceException(String provinceId) {
    super(
        EXCEPTION_CODE,
        "No such province :" + provinceId,
        BAD_REQUEST
    );
  }
}
