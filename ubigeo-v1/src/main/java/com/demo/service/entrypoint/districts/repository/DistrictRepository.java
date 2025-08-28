package com.demo.service.entrypoint.districts.repository;

import java.util.function.Predicate;

import com.demo.commons.serialization.JsonSerializer;
import com.demo.service.commons.properties.ApplicationProperties;
import com.demo.service.commons.enums.UbigeoType;
import com.demo.service.entrypoint.districts.repository.entity.DistrictEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class DistrictRepository {

    private final ApplicationProperties properties;
    private final JsonSerializer jsonSerializer;

    public Flux<DistrictEntity> findByProvinceIdAndDepartmentId(String provinceId, String departmentId) {
        return findAll()
            .filter(filterByProvinceId(provinceId).and(filterByDepartmentId(departmentId)));
    }

    public Flux<DistrictEntity> findAll() {
        String districtPath = properties.getFilePaths().get(UbigeoType.DISTRICTS.getLabel());
        return jsonSerializer.readListFromFile(districtPath, DistrictEntity.class);
    }

    private static Predicate<DistrictEntity> filterByProvinceId(String provinceId) {
        return district -> provinceId.equals(district.getProvinceId());
    }

    private static Predicate<DistrictEntity> filterByDepartmentId(String departmentId) {
        return district -> departmentId.equals(district.getDepartmentId());
    }
}
