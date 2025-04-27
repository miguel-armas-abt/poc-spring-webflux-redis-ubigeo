package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.constants.Symbol;
import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NoSuchCacheConfigException extends GenericException {

  public NoSuchCacheConfigException(String cacheName) {
    super(ErrorDictionary.NO_SUCH_CACHE_CONFIG.getMessage() + Symbol.COLON_WITH_SPACE + cacheName, ErrorDictionary.parse(NoSuchCacheConfigException.class));
  }
}
