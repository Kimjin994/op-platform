package com.design.elegant;

import com.design.elegant.service.IChargeModelHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@SpringBootApplication
@EnablePluginRegistries(value = {IChargeModelHandler.class})
public class ElegantApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElegantApplication.class, args);
  }

}
