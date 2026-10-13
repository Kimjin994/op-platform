package com.only4play.imserver.server;

import cn.hutool.extra.spring.SpringUtil;
import com.only4play.imserver.handler.MessageHandler;
import com.only4play.imserver.handler.UserAuthHandler;
import com.only4play.imserver.infrastructure.config.WebsocketProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IMServer {

  public void start() {
    NioEventLoopGroup boss = new NioEventLoopGroup();
    NioEventLoopGroup work = new NioEventLoopGroup();
    try {
      WebsocketProperties properties = SpringUtil.getBean(WebsocketProperties.class);
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(boss, work)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
              ch.pipeline().addLast("http编解码",new HttpServerCodec());
              ch.pipeline().addLast(new ChunkedWriteHandler());
              ch.pipeline().addLast(new HttpObjectAggregator(100 * 1024 * 1024));
              //When using websocket compression, you need to set allowExtension to true when adding WebSocketServerProtocolHandler and WebSocketClientProtocolHandler.
              // ch.pipeline().addLast("数据压缩",new WebSocketServerCompressionHandler());
              ch.pipeline().addLast("用户校验", new UserAuthHandler());
              ch.pipeline()
                  .addLast("websocket 协议处理",new WebSocketServerProtocolHandler(properties.getPath()));
              ch.pipeline().addLast(new MessageHandler());
            }
          });

      Channel channel = bootstrap.bind(properties.getPort()).sync().channel();
      log.info("websocket 启动成功，端口号:{},上下文:{}", properties.getPort(),
          properties.getPath());
      channel.closeFuture().sync();
    } catch (InterruptedException e) {
      log.error("启动异常", e);
    } finally {
      boss.shutdownGracefully();
      work.shutdownGracefully();
      log.info("websocket服务器已关闭");
    }
  }
}
