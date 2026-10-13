package com.only4play.system.infrastructure.security;

import cn.hutool.jwt.JWT;
import com.only4play.security.config.AuthErrorMsg;
import com.only4play.security.exception.CustomAuthenticationException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

public final class JwtTokens {

  private JwtTokens() {
  }

  public static String sign(Map<String, Object> payload, String secret, Integer expireMinutes) {
    int minutes = expireMinutes == null ? 120 : expireMinutes;
    JWT jwt = JWT.create();
    payload.forEach(jwt::setPayload);
    jwt.setExpiresAt(Date.from(Instant.now().plus(minutes, ChronoUnit.MINUTES)));
    jwt.setKey(secret.getBytes(StandardCharsets.UTF_8));
    return jwt.sign();
  }

  public static void assertNotExpired(JWT jwt) {
    Object exp = jwt.getPayload("exp");
    if (exp == null) {
      throw expired();
    }
    long expMillis;
    if (exp instanceof Date) {
      expMillis = ((Date) exp).getTime();
    } else if (exp instanceof Number) {
      long value = ((Number) exp).longValue();
      expMillis = value < 10_000_000_000L ? value * 1000 : value;
    } else {
      long value = Long.parseLong(exp.toString());
      expMillis = value < 10_000_000_000L ? value * 1000 : value;
    }
    if (System.currentTimeMillis() > expMillis) {
      throw expired();
    }
  }

  private static CustomAuthenticationException expired() {
    return new CustomAuthenticationException(AuthErrorMsg.tokenExpired.getCode(),
        AuthErrorMsg.tokenExpired.getName());
  }
}
