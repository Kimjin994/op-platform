package com.only4play.imserver.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "op.ws")
@Data
public class WebsocketProperties {

  private Integer port;

  private String path;

}
