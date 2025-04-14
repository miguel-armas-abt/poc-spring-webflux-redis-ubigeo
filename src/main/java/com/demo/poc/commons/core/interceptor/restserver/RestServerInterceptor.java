package com.demo.poc.commons.core.interceptor.restserver;

import com.demo.poc.commons.core.logging.ThreadContextInjector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class RestServerInterceptor implements WebFilter {

  private final ThreadContextInjector threadContextInjector;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    String url = exchange.getRequest().getURI().toString();

    generateTraceOfRequest(exchange.getRequest(), url);
    return chain.filter(exchange).doOnSuccess(aVoid -> generateTraceOfResponse(exchange.getResponse(), url));
    }

    private void generateTraceOfRequest(ServerHttpRequest serverHttpRequest, String url) {
      threadContextInjector.populateFromRestServerRequest(
          serverHttpRequest.getMethod().toString(),
          url,
          serverHttpRequest.getHeaders().toSingleValueMap(),
          "ToDo");
    }

    private void generateTraceOfResponse(ServerHttpResponse serverHttpResponse, String uri) {
      threadContextInjector.populateFromRestServerResponse(
          serverHttpResponse.getHeaders().toSingleValueMap(),
          uri,
          "ToDo",
          serverHttpResponse.getStatusCode().toString());
    }
}
