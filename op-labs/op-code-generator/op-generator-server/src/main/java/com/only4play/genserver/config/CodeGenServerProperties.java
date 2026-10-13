package com.only4play.genserver.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties("codegen.admin")
@Data
@Component
public class CodeGenServerProperties {

  private ServerProperties server = new ServerProperties();

  @Data
  public static class ServerProperties {

    private boolean enabled = false;

  }

}
