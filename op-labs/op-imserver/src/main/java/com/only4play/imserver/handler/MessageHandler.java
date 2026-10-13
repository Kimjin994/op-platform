package com.only4play.imserver.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.only4play.imserver.infrastructure.model.UserContext;
import com.only4play.imserver.infrastructure.session.ISessionStore;
import com.only4play.imserver.infrastructure.session.IUserChannelStore;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务逻辑处理handler
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到消息："+msg);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(
            Instant.now().toEpochMilli() + ":" + "收到消息");
        ctx.writeAndFlush(textWebSocketFrame);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开连接
        ISessionStore sessionStore = SpringUtil.getBean(ISessionStore.class);
        sessionStore.removeChannel(ctx.channel());
        log.info("客户端断开连接："+ctx.channel());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IUserChannelStore userChannelStore = SpringUtil.getBean(IUserChannelStore.class);
        if (evt instanceof UserContext){
            log.info("用户校验通过");
        }
        else if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            log.info("完成握手");
            //添加连接
            ISessionStore sessionStore = SpringUtil.getBean(ISessionStore.class);
            sessionStore.addChannel(ctx.channel());
            log.info("客户端加入连接："+ctx.channel());
            UserContext userContext = ctx.channel()
                .attr(UserAuthHandler.USER_CONTEXT_ATTRIBUTE_KEY).get();
            userChannelStore.bindUserToChannel(userContext.getUserId(), ctx.channel());
            log.info("用户与channel绑定成功");
        }
        super.userEventTriggered(ctx, evt);
    }
}
