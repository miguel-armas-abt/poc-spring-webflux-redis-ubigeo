package com.demo.poc.entrypoint.districts.rest;

import java.util.Map;

import com.demo.poc.commons.core.restserver.ServerResponseBuilder;
import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.headers.HeaderValidator;
import com.demo.poc.commons.core.validations.params.ParamValidator;
import com.demo.poc.entrypoint.districts.dto.params.DistrictParam;
import com.demo.poc.entrypoint.districts.repository.entity.DistrictEntity;
import com.demo.poc.entrypoint.districts.service.DistrictService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.demo.poc.commons.core.restclient.utils.HttpHeadersFiller.extractHeadersAsMap;
import static com.demo.poc.commons.core.restclient.utils.QueryParamFiller.extractQueryParamsAsMap;

@Component
@RequiredArgsConstructor
public class DistrictHandler {

  private final DistrictService districtService;
  private final HeaderValidator headerValidator;
  private final ParamValidator paramValidator;

  public Mono<ServerResponse> findByProvinceIdAndDepartmentId(ServerRequest serverRequest) {
    Map<String, String> headers = extractHeadersAsMap(serverRequest);
    headerValidator.validate(headers, DefaultHeaders.class);

    DistrictParam params = paramValidator.validateAndRetrieve(extractQueryParamsAsMap(serverRequest), DistrictParam.class);

    return ServerResponseBuilder.buildFlux(
        ServerResponse.ok(),
        serverRequest.headers(),
        DistrictEntity.class,
        districtService.findByProvinceIdAndDepartmentId(params.getProvinceId(), params.getDepartmentId())
    );
  }
}
