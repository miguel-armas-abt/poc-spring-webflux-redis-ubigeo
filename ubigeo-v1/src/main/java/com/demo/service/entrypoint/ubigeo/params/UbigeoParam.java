package com.demo.service.entrypoint.ubigeo.params;

import java.io.Serializable;

import com.demo.service.commons.constants.RegexConstants;
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
public class UbigeoParam implements Serializable {

  @Pattern(regexp = RegexConstants.UBIGEO_ID_PATTERN)
  @NotEmpty
  private String departmentId;

  @Pattern(regexp = RegexConstants.UBIGEO_ID_PATTERN)
  @NotEmpty
  private String provinceId;

  @Pattern(regexp = RegexConstants.UBIGEO_ID_PATTERN)
  @NotEmpty
  private String districtId;
}
