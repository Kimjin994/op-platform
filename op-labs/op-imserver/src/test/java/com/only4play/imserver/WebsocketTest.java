package com.only4play.imserver;

import io.netty.buffer.ByteBufAllocator;
import java.net.URI;
import java.nio.charset.Charset;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

/**
 * websocket 测试技术
 */
public class WebsocketTest {

  private WebSocketClient client = new ReactorNettyWebSocketClient();


  @Test
  public void testSend() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("token","6666");
    client.execute(
            URI.create("ws://localhost:9999/ws"),
            headers,
            session -> session.send(
                    Mono.just(session.textMessage("test send")))
                .thenMany(session.receive().map(wmg -> wmg.getPayloadAsText()).log())
                .then())
        .block(Duration.ofSeconds(600L));
  }


  @Test
  public void testSendBinary() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("token","111");
    NettyDataBufferFactory factory = new NettyDataBufferFactory(
        ByteBufAllocator.DEFAULT);
    client.execute(
            URI.create("ws://localhost:9999/ws"),
            headers,
            session -> session.send(
                    Mono.just(session.binaryMessage(f -> f.wrap("hello".getBytes(Charset.defaultCharset()))))
                )
                .thenMany(
                    session.receive().log())
                .then())
        .block(Duration.ofSeconds(10000L));
  }



}
