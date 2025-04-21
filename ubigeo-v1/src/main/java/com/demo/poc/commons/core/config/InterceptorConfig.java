package com.demo.poc.commons.core.config;

import com.demo.poc.commons.core.interceptor.error.ErrorInterceptor;
import com.demo.poc.commons.core.interceptor.restclient.request.RestClientRequestInterceptor;
import com.demo.poc.commons.core.interceptor.restclient.response.RestClientResponseInterceptor;
import com.demo.poc.commons.core.interceptor.restserver.RestServerInterceptor;
import com.demo.poc.commons.core.logging.ThreadContextInjector;
import com.demo.poc.commons.core.serialization.ByteSerializer;
import com.demo.poc.commons.custom.properties.ApplicationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig {

  @Bean
  public ErrorInterceptor errorInterceptor(ByteSerializer byteSerializer,
                                           ThreadContextInjector threadContextInjector,
                                           ApplicationProperties properties) {
    return new ErrorInterceptor(byteSerializer, threadContextInjector, properties);
  }

  @Bean
  public RestClientRequestInterceptor restClientRequestInterceptor(ThreadContextInjector threadContextInjector) {
    return new RestClientRequestInterceptor(threadContextInjector);
  }

  @Bean
  public RestClientResponseInterceptor restClientResponseInterceptor(ThreadContextInjector threadContextInjector) {
    return new RestClientResponseInterceptor(threadContextInjector);
  }

  @Bean
  public RestServerInterceptor restServerInterceptor(ThreadContextInjector threadContextInjector) {
    return new RestServerInterceptor(threadContextInjector);
  }

}
