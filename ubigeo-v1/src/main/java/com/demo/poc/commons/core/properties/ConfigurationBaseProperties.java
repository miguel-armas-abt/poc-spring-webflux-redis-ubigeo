package com.demo.poc.commons.core.properties;

import java.util.Map;
import java.util.Optional;

import com.demo.poc.commons.core.errors.exceptions.NoSuchRestClientException;
import com.demo.poc.commons.core.properties.restclient.RestClient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ConfigurationBaseProperties {

  protected Map<String, String> errorMessages;

  protected Map<String, RestClient> restClients;

  public RestClient searchRestClient(String serviceName) {
    return Optional.ofNullable(restClients.get(serviceName))
        .orElseThrow(NoSuchRestClientException::new);
  }
}