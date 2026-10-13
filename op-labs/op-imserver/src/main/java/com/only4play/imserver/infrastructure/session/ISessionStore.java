package com.only4play.imserver.infrastructure.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

public interface ISessionStore {

  ChannelGroup channelGroup();

  void addChannel(Channel channel);

  void removeChannel(Channel channel);

  Channel findChannel(String id);

}
