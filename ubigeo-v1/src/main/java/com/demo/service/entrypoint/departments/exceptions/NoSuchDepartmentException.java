package com.demo.service.entrypoint.departments.exceptions;

import com.demo.commons.errors.exceptions.GenericException;

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
