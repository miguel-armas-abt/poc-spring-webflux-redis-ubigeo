package com.demo.service.entrypoint.ubigeo.rest;

import com.demo.service.commons.constants.RestConstants;

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
public class UbigeoRestService {

  @Bean("ubigeo")
  public RouterFunction<ServerResponse> build(UbigeoHandler ubigeoHandler) {
    return nest(
        path(RestConstants.BASE_URI),
        route()
            .GET("/ubigeo", accept(APPLICATION_NDJSON), ubigeoHandler::findUbigeo)
            .build()
    );
  }
}
