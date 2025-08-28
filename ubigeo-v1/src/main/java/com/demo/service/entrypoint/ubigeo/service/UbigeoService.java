package com.demo.service.entrypoint.ubigeo.service;

import com.demo.service.entrypoint.ubigeo.dto.response.UbigeoResponseDto;
import reactor.core.publisher.Mono;

public interface UbigeoService {

  Mono<UbigeoResponseDto> findUbigeo(String ubigeoCode);
}
