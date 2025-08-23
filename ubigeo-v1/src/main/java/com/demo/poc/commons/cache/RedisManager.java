package com.demo.poc.commons.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisManager {

  private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

  public Mono<Boolean> isRedisAvailable() {
    return reactiveRedisTemplate.getConnectionFactory()
        .getReactiveConnection()
        .ping()
        .map("PONG"::equalsIgnoreCase)
        .onErrorResume(ex -> {
          log.error(ex.getMessage(), ex);
          return Mono.just(false);
        });
  }
}
