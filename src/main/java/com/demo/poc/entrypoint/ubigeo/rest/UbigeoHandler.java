package com.demo.poc.entrypoint.ubigeo.rest;

import java.util.Map;

import com.demo.poc.commons.core.restserver.ServerResponseBuilder;
import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.headers.HeaderValidator;
import com.demo.poc.commons.core.validations.params.ParamValidator;
import com.demo.poc.entrypoint.ubigeo.dto.params.UbigeoParam;
import com.demo.poc.entrypoint.ubigeo.service.UbigeoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.demo.poc.commons.core.restclient.utils.HttpHeadersFiller.extractHeadersAsMap;
import static com.demo.poc.commons.core.restclient.utils.QueryParamFiller.extractQueryParamsAsMap;

@Component
@RequiredArgsConstructor
public class UbigeoHandler {

  private final UbigeoService ubigeoService;
  private final HeaderValidator headerValidator;
  private final ParamValidator paramValidator;

  public Mono<ServerResponse> findUbigeo(ServerRequest serverRequest) {
    Map<String, String> headers = extractHeadersAsMap(serverRequest);
    headerValidator.validate(headers, DefaultHeaders.class);

    UbigeoParam params = paramValidator.validateAndRetrieve(extractQueryParamsAsMap(serverRequest), UbigeoParam.class);

    String ubigeoCode = params.getDepartmentId() + params.getProvinceId() + params.getDistrictId();
    return ubigeoService.findUbigeo(ubigeoCode)
        .flatMap(response -> ServerResponseBuilder
            .buildMono(
                ServerResponse.ok(),
                serverRequest.headers(),
                response));
  }
}
