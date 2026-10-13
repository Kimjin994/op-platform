package com.only4play.sample.config;

import com.only4play.commons.engine.IGenerateEngine;
import com.only4play.commons.engine.impl.MustacheGenerateEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeGenConfiguration {


  @Bean
  public IGenerateEngine generateEngine(){
    return new MustacheGenerateEngine();
  }

}
