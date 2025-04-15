package com.demo.poc.entrypoint.provinces.rest;

import java.util.Map;

import com.demo.poc.commons.core.restserver.ServerResponseBuilder;
import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.headers.HeaderValidator;
import com.demo.poc.commons.core.validations.params.ParamValidator;
import com.demo.poc.entrypoint.provinces.dto.params.ProvinceParam;
import com.demo.poc.entrypoint.provinces.repository.entity.ProvinceEntity;
import com.demo.poc.entrypoint.provinces.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.demo.poc.commons.core.restclient.utils.HttpHeadersFiller.extractHeadersAsMap;
import static com.demo.poc.commons.core.restclient.utils.QueryParamFiller.extractQueryParamsAsMap;

@Component
@RequiredArgsConstructor
public class ProvinceHandler {

  private final ProvinceService provinceService;
  private final HeaderValidator headerValidator;
  private final ParamValidator paramValidator;

  public Mono<ServerResponse> findByDepartmentId(ServerRequest serverRequest) {
    Map<String, String> headers = extractHeadersAsMap(serverRequest);
    headerValidator.validate(headers, DefaultHeaders.class);

    ProvinceParam params = paramValidator.validateAndRetrieve(extractQueryParamsAsMap(serverRequest), ProvinceParam.class);

    return ServerResponseBuilder.buildFlux(
        ServerResponse.ok(),
        serverRequest.headers(),
        ProvinceEntity.class,
        provinceService.findByDepartmentId(params.getDepartmentId())
    );
  }
}
