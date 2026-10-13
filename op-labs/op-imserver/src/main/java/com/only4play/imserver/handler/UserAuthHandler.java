package com.only4play.imserver.handler;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

import cn.hutool.extra.spring.SpringUtil;
import com.only4play.imserver.infrastructure.model.UserContext;
import com.only4play.imserver.infrastructure.service.IUserService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户认证handler
 */
@Slf4j
@ChannelHandler.Sharable
public class UserAuthHandler extends ChannelInboundHandlerAdapter {

  public static final AttributeKey<UserContext> USER_CONTEXT_ATTRIBUTE_KEY =
      AttributeKey.valueOf("USER_CONTEXT_ATTRIBUTE_KEY");

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg)
      throws Exception {
    if (msg instanceof FullHttpMessage) {
      HttpHeaders headers = ((FullHttpMessage) msg).headers();
      String token = Objects.requireNonNull(headers.get("token"));
      //获取token
      IUserService userService = SpringUtil.getBean(IUserService.class);
      Optional<UserContext> userContext = userService.parseToken(token);
      if (userContext.isPresent()) {
        ctx.channel().attr(USER_CONTEXT_ATTRIBUTE_KEY).set(userContext.get());
        ctx.fireUserEventTriggered(userContext.get());
      } else {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN);
        sendHttpResponse(ctx, (FullHttpRequest) msg, response);
      }
    }
    super.channelRead(ctx, msg);
  }

  private static void sendHttpResponse(ChannelHandlerContext ctx,
      FullHttpRequest req, DefaultFullHttpResponse res) {
    if (res.status().code() != HttpResponseStatus.OK.code()) {
      ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),
          StandardCharsets.UTF_8);
      res.content().writeBytes(buf);
      buf.release();
    }
    ChannelFuture f = ctx.channel().writeAndFlush(res);
    if (!isKeepAlive(req) || res.status().code() != HttpResponseStatus.OK.code()) {
      f.addListener(ChannelFutureListener.CLOSE);
    }
  }
}
