package com.demo.poc.entrypoint.ubigeo.service;

import com.demo.poc.entrypoint.ubigeo.dto.response.UbigeoResponseDto;
import reactor.core.publisher.Mono;

public interface UbigeoService {

  Mono<UbigeoResponseDto> findUbigeo(String ubigeoCode);
}
