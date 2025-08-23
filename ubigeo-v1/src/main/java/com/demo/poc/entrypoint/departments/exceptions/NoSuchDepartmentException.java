package com.demo.poc.entrypoint.departments.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NoSuchDepartmentException extends GenericException {

  private static final String EXCEPTION_CODE = "10.01.09";

  public NoSuchDepartmentException(String departmentId) {
    super(
        EXCEPTION_CODE,
        "No such department: " + departmentId,
        BAD_REQUEST
    );
  }
}
