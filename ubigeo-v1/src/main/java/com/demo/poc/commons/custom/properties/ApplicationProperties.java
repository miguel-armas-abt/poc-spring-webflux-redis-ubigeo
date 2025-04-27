package com.demo.poc.commons.custom.properties;

import java.util.Map;
import java.util.Optional;

import com.demo.poc.commons.core.properties.ConfigurationBaseProperties;
import com.demo.poc.commons.custom.exceptions.NoSuchCacheConfigException;
import com.demo.poc.commons.custom.properties.cache.CacheTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "configuration")
public class ApplicationProperties extends ConfigurationBaseProperties {

  private Map<String, CacheTemplate> cache;

  private Map<String, String> filePaths;

  public CacheTemplate searchCache(String cacheName) {
    return Optional.ofNullable(cache.get(cacheName))
        .orElseThrow(() -> new NoSuchCacheConfigException(cacheName));
  }
}
