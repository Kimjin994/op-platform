package com.only4play.system;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.only4play.system.domain.template.mockrule.domainservice.plugin.IMockPlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author gim
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableElasticsearchRepositories(basePackages = "com.only4play.system.infrastructure.elastic")
@EnableSpringUtil
@EnableJpaRepositories(basePackages = {"com.only4play.system.domain"})
@EnablePluginRegistries(value = {IMockPlugin.class})
public class SystemCenterApplication {

  public static void main(String[] args) {
    SpringApplication.run(SystemCenterApplication.class, args);
  }


  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
