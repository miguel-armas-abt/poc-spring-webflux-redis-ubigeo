package com.demo.service.entrypoint.provinces.repository.entity;

import com.demo.service.entrypoint.districts.repository.entity.DistrictEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvinceEntity implements Serializable {

    private String id;
    private String description;
    private String departmentId;
    private List<DistrictEntity> districts;
}
