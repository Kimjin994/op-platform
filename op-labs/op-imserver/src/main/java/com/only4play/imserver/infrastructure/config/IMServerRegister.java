package com.only4play.imserver.infrastructure.config;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class IMServerRegister implements CommandLineRunner {

  private final  NacosServiceManager nacosServiceManager;

  private final WebsocketProperties websocketProperties;

  @Override
  public void run(String... args) throws Exception {
    InetUtils inetUtils = new InetUtils(new InetUtilsProperties());
    NamingService namingService = nacosServiceManager.getNamingService(null);
    namingService.registerInstance("ws", inetUtils.findFirstNonLoopbackAddress().getHostAddress(),websocketProperties.getPort());
    log.info("ws socket 服务nacos注册成功");
  }
}
