package com.only4play.system.infrastructure.security.authorization;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class PermissionCache {

  private final Map<Long, Collection<GrantedAuthority>> authorities = new ConcurrentHashMap<>();

  public Optional<Collection<GrantedAuthority>> get(Long userId) {
    return Optional.ofNullable(authorities.get(userId));
  }

  public void put(Long userId, Collection<GrantedAuthority> granted) {
    authorities.put(userId, granted);
  }

  public void evict(Long userId) {
    if (userId != null) {
      authorities.remove(userId);
    }
  }
}
