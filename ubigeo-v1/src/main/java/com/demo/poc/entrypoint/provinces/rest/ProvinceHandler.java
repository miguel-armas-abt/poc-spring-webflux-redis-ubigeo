package com.demo.poc.entrypoint.provinces.rest;

import java.util.Map;

import com.demo.poc.commons.core.restserver.utils.RestServerUtils;
import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.ParamValidator;
import com.demo.poc.entrypoint.provinces.params.ProvinceParam;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import com.demo.poc.entrypoint.provinces.service.ProvinceService;
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
public class ProvinceHandler {

  private final ProvinceService provinceService;
  private final ParamValidator paramValidator;

  public Mono<ServerResponse> findByDepartmentId(ServerRequest serverRequest) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(serverRequest);


    Mono<ProvinceParam> params = paramValidator.validateAndGet(RestServerUtils.extractQueryParamsAsMap(serverRequest), ProvinceParam.class);

    Flux<ProvinceEntity> response = paramValidator.validateAndGet(headers, DefaultHeaders.class)
        .zipWith(params)
        .flatMapMany(tuple -> provinceService.findByDepartmentId(tuple.getT2().getDepartmentId()));

    return ServerResponse.ok()
        .headers(httpHeaders -> RestServerUtils.buildResponseHeaders(serverRequest.headers()).accept(httpHeaders))
        .contentType(MediaType.APPLICATION_NDJSON)
        .body(BodyInserters.fromPublisher(response, ProvinceEntity.class));
  }
}
