package com.demo.poc.commons.properties.cache;

import java.time.Duration;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimeToLive {

  HALF_MINUTE(Duration.ofSeconds(30)),
  MINUTE(Duration.ofSeconds(60)),
  FIVE_MINUTES(Duration.ofSeconds(300)),
  HALF_HOUR(Duration.ofSeconds(1800)),
  HOUR(Duration.ofSeconds(3600)),
  DAY(Duration.ofSeconds(86400));

  private final Duration timeToLive;

  public static Duration getTtl(TimeToLive timeToLive) {
    return Optional.ofNullable(timeToLive.getTimeToLive())
        .orElseGet(TimeToLive.FIVE_MINUTES::getTimeToLive);
  }
}