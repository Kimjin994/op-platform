package com.only4play.system.infrastructure.security.authorization;

import com.only4play.system.infrastructure.constants.AuthConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("perm")
public class PermissionService {

  public boolean has(String code) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getAuthorities() == null) {
      return false;
    }
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      String granted = authority.getAuthority();
      if (AuthConstants.SUPER_ADMIN_ROLE.equals(granted)
          || ("ROLE_" + AuthConstants.SUPER_ADMIN_ROLE).equals(granted)
          || code.equals(granted)) {
        return true;
      }
    }
    return false;
  }
}
