package com.demo.poc.commons.custom.enums;

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
