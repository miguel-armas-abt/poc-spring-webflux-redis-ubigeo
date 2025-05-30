package com.demo.poc.entrypoint.departments.rest;

import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.ParamValidator;
import com.demo.poc.entrypoint.departments.repository.entity.DepartmentEntity;
import com.demo.poc.entrypoint.departments.service.DepartmentService;
import com.demo.poc.commons.core.restserver.utils.RestServerUtils;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DepartmentHandler {

  private final DepartmentService departmentService;
  private final ParamValidator headerValidator;

  public Mono<ServerResponse> findAllDepartments(ServerRequest serverRequest) {
    Map<String, String> headers = RestServerUtils.extractHeadersAsMap(serverRequest);

    Flux<DepartmentEntity> response = headerValidator.validateAndGet(headers, DefaultHeaders.class)
        .flatMapMany(defaultHeaders -> departmentService.findAllDepartments());

    return ServerResponse.ok()
        .headers(httpHeaders -> RestServerUtils.buildResponseHeaders(serverRequest.headers()).accept(httpHeaders))
        .contentType(MediaType.APPLICATION_NDJSON)
        .body(BodyInserters.fromPublisher(response, DepartmentEntity.class));
  }
}
