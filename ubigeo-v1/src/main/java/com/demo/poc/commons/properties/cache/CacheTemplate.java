package com.demo.poc.commons.properties.cache;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheTemplate implements Serializable {

  private TimeToLive timeToLive;
  private String keyPrefix;

}