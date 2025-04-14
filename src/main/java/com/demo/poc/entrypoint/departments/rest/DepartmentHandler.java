package com.demo.poc.entrypoint.departments.rest;

import com.demo.poc.commons.core.validations.headers.DefaultHeaders;
import com.demo.poc.commons.core.validations.headers.HeaderValidator;
import com.demo.poc.entrypoint.departments.repository.entity.DepartmentEntity;
import com.demo.poc.entrypoint.departments.service.DepartmentService;
import com.demo.poc.commons.core.restserver.ServerResponseBuilder;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.demo.poc.commons.core.restclient.utils.HttpHeadersFiller.extractHeadersAsMap;

@Component
@RequiredArgsConstructor
public class DepartmentHandler {

  private final DepartmentService departmentService;
  private final HeaderValidator headerValidator;

  public Mono<ServerResponse> findAllDepartments(ServerRequest serverRequest) {
    Map<String, String> headers = extractHeadersAsMap(serverRequest);
    headerValidator.validate(headers, DefaultHeaders.class);

    return ServerResponseBuilder.buildFlux(
        ServerResponse.ok(),
        serverRequest.headers(),
        DepartmentEntity.class,
        departmentService.findAllDepartments()
    );
  }
}
