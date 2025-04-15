package com.demo.poc.entrypoint.provinces.rest;

import com.demo.poc.commons.custom.constants.RestConstants;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_NDJSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProvinceRestService {

  @Bean("provinces")
  public RouterFunction<ServerResponse> build(ProvinceHandler provinceHandler) {
    return nest(
        path(RestConstants.BASE_URI),
        route()
            .GET("/provinces", accept(APPLICATION_NDJSON), provinceHandler::findByDepartmentId)
            .build()
    );
  }
}
