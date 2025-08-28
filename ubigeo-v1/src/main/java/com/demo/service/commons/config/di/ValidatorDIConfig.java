package com.demo.service.commons.config.di;

import com.demo.commons.validations.BodyValidator;
import com.demo.commons.validations.ParamMapper;
import com.demo.commons.validations.ParamValidator;
import com.demo.commons.validations.headers.DefaultHeadersMapper;
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