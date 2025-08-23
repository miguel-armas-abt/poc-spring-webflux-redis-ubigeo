package com.demo.poc.commons.config.di;

import com.demo.poc.commons.core.validations.BodyValidator;
import com.demo.poc.commons.core.validations.ParamMapper;
import com.demo.poc.commons.core.validations.ParamValidator;
import com.demo.poc.commons.core.validations.headers.DefaultHeadersMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import java.util.List;

@Configuration
public class ValidatorDIConfig {

  @Bean
  public BodyValidator requestValidator(Validator validator) {
    return new BodyValidator();
  }

  @Bean
  public DefaultHeadersMapper defaultHeadersMapper() {
    return new DefaultHeadersMapper();
  }

  @Bean
  public ParamValidator paramValidator(List<ParamMapper> mappers, BodyValidator bodyValidator) {
    return new ParamValidator(mappers, bodyValidator);
  }
}