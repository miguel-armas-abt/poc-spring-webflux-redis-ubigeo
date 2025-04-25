package com.demo.poc.entrypoint.districts.params;

import java.io.Serializable;

import com.demo.poc.commons.custom.constants.RegexConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DistrictParam implements Serializable {

  @Pattern(regexp = RegexConstants.UBIGEO_ID_PATTERN)
  @NotEmpty
  private String departmentId;

  @Pattern(regexp = RegexConstants.UBIGEO_ID_PATTERN)
  @NotEmpty
  private String provinceId;
}
