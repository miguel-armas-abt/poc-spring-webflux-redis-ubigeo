package com.demo.poc.entrypoint.districts.rest;

import java.util.Map;

import com.demo.poc.commons.core.restserver.utils.RestServerUtils;
import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.ParamValidator;
import com.demo.poc.entrypoint.districts.params.DistrictParam;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import com.demo.poc.entrypoint.districts.service.DistrictService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class DistrictHandler {

  private final DistrictService districtService;
  private final ParamValidator paramValidator;

  public Mono<ServerResponse> findByProvinceIdAndDepartmentId(ServerRequest serverRequest) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(serverRequest);

    Mono<DistrictParam> params = paramValidator.validateAndGet(RestServerUtils.extractQueryParamsAsMap(serverRequest), DistrictParam.class);

    Flux<DistrictEntity> response = paramValidator.validateAndGet(headers, DefaultHeaders.class)
        .zipWith(params)
        .flatMapMany(tuple -> districtService.findByProvinceIdAndDepartmentId(tuple.getT2().getProvinceId(), tuple.getT2().getDepartmentId()));

    return ServerResponse.ok()
        .headers(httpHeaders -> RestServerUtils.buildResponseHeaders(serverRequest.headers()).accept(httpHeaders))
        .contentType(MediaType.APPLICATION_NDJSON)
        .body(BodyInserters.fromPublisher(response, DistrictEntity.class));
  }
}
