package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NoSuchProvinceException extends GenericException {

  public NoSuchProvinceException(String provinceId) {
    super("provinceId: " + provinceId, ErrorDictionary.parse(NoSuchProvinceException.class));
  }
}
