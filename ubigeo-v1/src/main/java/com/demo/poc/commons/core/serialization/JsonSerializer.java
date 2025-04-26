package com.demo.poc.commons.core.serialization;

import com.demo.poc.commons.core.errors.exceptions.JsonReadException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JsonSerializer {

  private final ObjectMapper objectMapper;

  @PostConstruct
  private void configureObjectMapper() {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }

  protected static InputStream getResourceAsStream(String filePath) throws IOException {
    Resource resource = new ClassPathResource(filePath);
    return resource.getInputStream();
  }

  protected <T> T readValue(InputStream inputStream, Class<T> objectClass) {
    try (InputStream is = inputStream) {
      return objectMapper.readValue(is, objectClass);
    } catch (IOException ex) {
      throw new JsonReadException(ex.getMessage());
    }
  }

  protected <T> List<T> readList(InputStream inputStream, Class<T> objectClass) {
    try (InputStream is = inputStream) {
      CollectionType collectionType =
          objectMapper.getTypeFactory().constructCollectionType(List.class, objectClass);
      return objectMapper.readValue(is, collectionType);
    } catch (IOException ex) {
      throw new JsonReadException(ex.getMessage());
    }
  }

  public <T> Mono<T> readElementFromFile(String filePath, Class<T> objectClass) {
    return Mono.fromCallable(() -> getResourceAsStream(filePath))
        .subscribeOn(Schedulers.boundedElastic())
        .map(is -> readValue(is, objectClass));
  }

  public <T> Flux<T> readListFromFile(String filePath, Class<T> objectClass) {
    return Mono.fromCallable(() -> getResourceAsStream(filePath))
        .subscribeOn(Schedulers.boundedElastic())
        .map(is -> readList(is, objectClass))
        .flatMapMany(Flux::fromIterable);
  }
}