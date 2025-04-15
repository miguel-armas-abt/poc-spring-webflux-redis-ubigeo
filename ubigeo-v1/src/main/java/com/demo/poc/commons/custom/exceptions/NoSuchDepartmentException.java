package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NoSuchDepartmentException extends GenericException {

  public NoSuchDepartmentException(String departmentId) {
    super("departmentId: " + departmentId, ErrorDictionary.parse(NoSuchDepartmentException.class));
  }
}
