package com.only4play.system.infrastructure.security.authentication.admin.password;

import com.google.common.collect.Maps;
import com.only4play.security.base.BaseAuthenticationProvider;
import com.only4play.security.base.LoginSuccessToken;
import com.only4play.security.config.AuthErrorMsg;
import com.only4play.system.domain.admin.AdminUser;
import com.only4play.system.domain.admin.repository.AdminUserRepository;
import com.only4play.system.infrastructure.config.SecurityProperties;
import com.only4play.system.infrastructure.constants.AuthConstants;
import com.only4play.system.infrastructure.security.JwtTokens;
import com.only4play.system.infrastructure.security.LoginUserType;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
@Slf4j
@RequiredArgsConstructor
public class AdminPasswordAuthenticationProvider extends BaseAuthenticationProvider implements
    AuthenticationProvider {

  private final AdminUserRepository adminUserRepository;

  private final SecurityProperties securityProperties;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    AdminUsernamePasswordToken token = (AdminUsernamePasswordToken) authentication;
    if (ObjectUtils.isEmpty(token.getUsername()) || ObjectUtils.isEmpty(token.getPassword())) {
      throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
    }
    Optional<AdminUser> adminUser = adminUserRepository.findByPhone(token.getUsername());
    if(!adminUser.isPresent()){
      throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
    }else {
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      boolean matches = bCryptPasswordEncoder.matches(token.getPassword(), adminUser.get().getPassword());
      if(matches){
        Map<String,Object> jwtInfo = Maps.newHashMap();
        jwtInfo.put(AuthConstants.ID, adminUser.get().getId());
        jwtInfo.put(AuthConstants.LOGIN_USER_TYPE, LoginUserType.ADMIN_USER.getCode());
        String jwtToken = JwtTokens.sign(jwtInfo, securityProperties.getSecret(),
            securityProperties.getExpireMinutes());
        return new LoginSuccessToken(jwtToken, adminUser.get().getUsername());
      }else {
        throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
      }
    }
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return AdminUsernamePasswordToken.class.isAssignableFrom(aClass);
  }
}
