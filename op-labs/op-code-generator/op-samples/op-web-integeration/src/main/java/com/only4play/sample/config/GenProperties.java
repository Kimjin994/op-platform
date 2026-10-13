package com.only4play.sample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codegen")
@Data
public class GenProperties {

  private String genBasePath;

  private String basePackage;

}
