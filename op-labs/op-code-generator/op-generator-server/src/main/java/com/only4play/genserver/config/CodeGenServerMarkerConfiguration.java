package com.only4play.genserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里是为了更严谨点，只有加载了这个类说明项目导入了server包，这是一个小技巧
 */
@Configuration(proxyBeanMethods = false)
public class CodeGenServerMarkerConfiguration {

  @Bean
  public Marker genServerMarker() {
    return new Marker();
  }

  public static class Marker {

  }

}
