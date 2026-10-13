package com.only4play.orderjob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBatchProcessing
public class OrderCenterJobApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderCenterJobApplication.class, args);
  }

}
