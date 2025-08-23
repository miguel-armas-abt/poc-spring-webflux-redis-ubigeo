package com.demo.poc.commons.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfig {

  @Bean
  public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
    StringRedisSerializer keySerializer = new StringRedisSerializer();
    GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();

    RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext
        .<String, Object>newSerializationContext(keySerializer)
        .hashKey(keySerializer)
        .hashValue(valueSerializer)
        .value(valueSerializer)
        .build();

    return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
  }
}

