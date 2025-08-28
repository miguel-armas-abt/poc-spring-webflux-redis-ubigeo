package com.demo.service.commons.properties.cache;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheTemplate implements Serializable {

  private TimeToLive timeToLive;
  private String keyPrefix;

}