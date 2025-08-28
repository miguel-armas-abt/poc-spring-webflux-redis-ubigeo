package com.demo.service.commons.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UbigeoType {

  DEPARTMENTS("departments"),
  PROVINCES("provinces"),
  DISTRICTS("districts"),;

  private final String label;


}
