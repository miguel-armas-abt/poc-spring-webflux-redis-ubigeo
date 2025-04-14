package com.demo.poc.entrypoint.provinces.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.demo.poc.commons.core.serialization.JsonSerializer;
import com.demo.poc.commons.custom.properties.ApplicationProperties;
import com.demo.poc.commons.custom.enums.UbigeoType;
import com.demo.poc.entrypoint.districts.repository.DistrictRepository;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProvinceRepository {

    private final DistrictRepository districtRepository;
    private final ApplicationProperties properties;
    private final JsonSerializer jsonSerializer;

    public Flux<ProvinceEntity> findByDepartmentId(boolean showDistricts, String departmentId) {
        return showDistricts
            ? findByPredicateWithDistricts(filterByDepartmentId(departmentId))
            : findByPredicate(filterByDepartmentId(departmentId));
    }

    private Flux<ProvinceEntity> findByPredicateWithDistricts(Predicate<ProvinceEntity> predicate) {
        Mono<Map<String, List<DistrictEntity>>> districtsGroupedByProvinceIdAndDepartmentId =
            districtRepository
                .findAll()
                .collect(Collectors.groupingBy(district -> districtMapKey.apply(district.getDepartmentId(), district.getProvinceId())));

        return findByPredicate(predicate)
            .flatMap(province -> districtsGroupedByProvinceIdAndDepartmentId
                .map(districtsMap -> {
                    province.setDistricts(districtsMap.getOrDefault(districtMapKey.apply(province.getDepartmentId(), province.getId()), List.of()));
                    return province;
                }));
    }

    private Flux<ProvinceEntity> findByPredicate(Predicate<ProvinceEntity> predicate) {
        return findAll().filter(predicate);
    }

    private Flux<ProvinceEntity> findAll() {
        String provincePath = properties.getFilePaths().get(UbigeoType.PROVINCES.getLabel());
        List<ProvinceEntity> departments = jsonSerializer.readListFromFile(provincePath, ProvinceEntity.class);
        return Flux.fromIterable(departments)
            .sort(Comparator.comparing(ProvinceEntity::getId));
    }

    private static Predicate<ProvinceEntity> filterByDepartmentId(String departmentId) {
        return province -> departmentId.equals(province.getDepartmentId());
    }

    private static final BinaryOperator<String> districtMapKey =
        (departmentId, provinceId) -> departmentId + "," + provinceId;
}
