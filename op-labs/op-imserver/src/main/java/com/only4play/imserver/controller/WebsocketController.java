package com.only4play.imserver.controller;

import com.only4play.imserver.infrastructure.session.ISessionStore;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.time.Instant;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "websocket")
@RequiredArgsConstructor
public class WebsocketController {

  private final ISessionStore sessionStore;

  @PostMapping(value = "sendMsg")
  @SneakyThrows
  public String sendMsg(@RequestBody PushMessage message) {
    TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(
        Instant.now().toEpochMilli() + ":" + message.getMessage());
    ChannelGroupFuture cgf = sessionStore.channelGroup().writeAndFlush(textWebSocketFrame);
    cgf.sync();
    return "发送成功";
  }


  @Data
  static class PushMessage{
    private String message;
  }


}
