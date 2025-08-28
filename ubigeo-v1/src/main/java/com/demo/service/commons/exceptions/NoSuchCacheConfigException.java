package com.demo.service.commons.exceptions;

import com.demo.commons.errors.exceptions.GenericException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class NoSuchCacheConfigException extends GenericException {

  private static final String EXCEPTION_CODE = "01.01.04";

  public NoSuchCacheConfigException(String cacheName) {
    super(
        EXCEPTION_CODE,
        "No such cache config: " + cacheName,
        INTERNAL_SERVER_ERROR
    );
  }
}
