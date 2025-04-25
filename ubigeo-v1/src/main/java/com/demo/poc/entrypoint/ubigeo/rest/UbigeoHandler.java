package com.demo.poc.entrypoint.ubigeo.rest;

import java.util.Map;

import com.demo.poc.commons.core.restserver.utils.RestServerUtils;
import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.ParamValidator;
import com.demo.poc.entrypoint.ubigeo.params.UbigeoParam;
import com.demo.poc.entrypoint.ubigeo.service.UbigeoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class UbigeoHandler {

  private final UbigeoService ubigeoService;
  private final ParamValidator paramValidator;

  public Mono<ServerResponse> findUbigeo(ServerRequest serverRequest) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(serverRequest);

    Mono<UbigeoParam> params = paramValidator.validateAndGet(RestServerUtils.extractQueryParamsAsMap(serverRequest), UbigeoParam.class);

    return paramValidator.validateAndGet(headers, DefaultHeaders.class)
        .zipWith(params)
        .map(tuple -> tuple.getT2().getDepartmentId() + tuple.getT2().getProvinceId() + tuple.getT2().getDistrictId())
        .flatMap(ubigeoService::findUbigeo)
        .flatMap(response -> ServerResponse.ok()
            .headers(httpHeaders -> RestServerUtils.buildResponseHeaders(serverRequest.headers()).accept(httpHeaders))
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(response)));
  }
}
