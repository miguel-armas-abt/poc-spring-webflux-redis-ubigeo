package com.demo.poc.entrypoint.districts.repository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.demo.poc.commons.core.serialization.JsonSerializer;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import com.demo.poc.commons.custom.enums.UbigeoType;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
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
        List<DistrictEntity> departments = jsonSerializer.readListFromFile(districtPath, DistrictEntity.class);
        return Flux.fromIterable(departments)
            .sort(Comparator.comparing(DistrictEntity::getId));
    }

    private static Predicate<DistrictEntity> filterByProvinceId(String provinceId) {
        return district -> provinceId.equals(district.getProvinceId());
    }

    private static Predicate<DistrictEntity> filterByDepartmentId(String departmentId) {
        return district -> departmentId.equals(district.getDepartmentId());
    }
}
