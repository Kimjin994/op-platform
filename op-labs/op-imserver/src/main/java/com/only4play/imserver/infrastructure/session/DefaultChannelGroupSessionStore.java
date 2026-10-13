package com.only4play.imserver.infrastructure.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class DefaultChannelGroupSessionStore implements ISessionStore{

  private static ChannelGroup channelGroup =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  private static ConcurrentHashMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

  @Override
  public ChannelGroup channelGroup() {
    return channelGroup;
  }

  @Override
  public void addChannel(Channel channel) {
    channelGroup.add(channel);
    channelMap.put(channel.id().asLongText(), channel.id());
  }

  @Override
  public void removeChannel(Channel channel) {
    channelGroup.remove(channel);
    channelMap.remove(channel.id().asLongText());

  }

  @Override
  public Channel findChannel(String id) {
    return channelGroup.find(channelMap.get(id));
  }
}
