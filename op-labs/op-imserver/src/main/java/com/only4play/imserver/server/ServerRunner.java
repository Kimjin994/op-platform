package com.only4play.imserver.server;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Async
public class ServerRunner implements SmartInitializingSingleton {

  @Override
  public void afterSingletonsInstantiated() {
    IMServer imServer = new IMServer();
    imServer.start();
  }
}
