package com.only4play.system.infrastructure.security.authentication.user;

import com.only4play.security.base.BaseUsernamePasswordToken;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class UserPasswordToken extends BaseUsernamePasswordToken {

  public UserPasswordToken(Collection<? extends GrantedAuthority> authorities, String username,
      String password) {
    super(authorities, username, password);
  }
}
