package com.only4play.imserver.infrastructure.session;

import io.netty.channel.Channel;
import java.util.Optional;

public interface IUserChannelStore {

  Optional<Channel> findChannelByUserId(Long userId);

  void bindUserToChannel(Long userId, Channel channel);

}
