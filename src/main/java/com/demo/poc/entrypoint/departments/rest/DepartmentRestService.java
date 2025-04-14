package com.demo.poc.entrypoint.departments.rest;

import static org.springframework.http.MediaType.APPLICATION_NDJSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class DepartmentRestService {

  private static final String BASE_URI = "/poc/ubigeo/v1";

  @Bean("departments")
  public RouterFunction<ServerResponse> build(DepartmentHandler departmentHandler) {
    return nest(
        path(BASE_URI),
        route()
            .GET("/departments", accept(APPLICATION_NDJSON), departmentHandler::findAllDepartments)
            .build()
    );
  }
}