package com.only4play.system.infrastructure.security.authentication.user;

import com.google.common.collect.Maps;
import com.only4play.security.base.BaseAuthenticationProvider;
import com.only4play.security.base.LoginSuccessToken;
import com.only4play.security.config.AuthErrorMsg;
import com.only4play.system.domain.user.UserAccount;
import com.only4play.system.domain.user.repository.UserAccountRepository;
import com.only4play.system.domain.user.repository.UserBaseRepository;
import com.only4play.system.infrastructure.config.SecurityProperties;
import com.only4play.system.infrastructure.constants.AuthConstants;
import com.only4play.system.infrastructure.security.JwtTokens;
import com.only4play.system.infrastructure.security.LoginUserType;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@RequiredArgsConstructor
public class UserPasswordAuthenticationProvider extends BaseAuthenticationProvider implements
    AuthenticationProvider {

  private final UserAccountRepository userAccountRepository;

  private final UserBaseRepository userBaseRepository;

  private final SecurityProperties securityProperties;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UserPasswordToken token = (UserPasswordToken) authentication;
    if (ObjectUtils.isEmpty(token.getUsername()) || ObjectUtils.isEmpty(token.getPassword())) {
      throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
    }
    Optional<UserAccount> account = userAccountRepository.findByAccountNo(token.getUsername());
    if (!account.isPresent() || !userBaseRepository.findById(account.get().getUserId()).isPresent()) {
      throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
    }
    if (!new BCryptPasswordEncoder().matches(token.getPassword(), account.get().getPassword())) {
      throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
    }
    Map<String, Object> jwtInfo = Maps.newHashMap();
    jwtInfo.put(AuthConstants.ID, account.get().getUserId());
    jwtInfo.put(AuthConstants.LOGIN_USER_TYPE, LoginUserType.CUSTOMER.getCode());
    String jwtToken = JwtTokens.sign(jwtInfo, securityProperties.getSecret(),
        securityProperties.getExpireMinutes());
    return new LoginSuccessToken(jwtToken, token.getUsername());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UserPasswordToken.class.isAssignableFrom(authentication);
  }
}
