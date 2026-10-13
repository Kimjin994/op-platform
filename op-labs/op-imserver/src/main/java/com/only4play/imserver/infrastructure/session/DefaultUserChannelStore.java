package com.only4play.imserver.infrastructure.session;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserChannelStore implements IUserChannelStore{

  private static ConcurrentHashMap<Long, Channel> userChannel = new ConcurrentHashMap<>();

  public static final AttributeKey<Long> USER_ID_ATTRIBUTE_KEY =
      AttributeKey.valueOf("USER_ID_ATTRIBUTE_KEY");

  @Override
  public Optional<Channel> findChannelByUserId(Long userId) {
    Channel channel = userChannel.get(userId);
    return Optional.ofNullable(channel);
  }

  @Override
  public void bindUserToChannel(Long userId, Channel channel) {
    userChannel.put(userId,channel);
    channel.attr(USER_ID_ATTRIBUTE_KEY).set(userId);
  }

}
