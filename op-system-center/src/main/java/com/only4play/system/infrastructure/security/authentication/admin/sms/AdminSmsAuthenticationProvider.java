package com.only4play.system.infrastructure.security.authentication.admin.sms;

import cn.hutool.jwt.JWTUtil;
import com.google.common.collect.Maps;
import com.only4play.security.base.BaseAuthenticationProvider;
import com.only4play.security.base.LoginSuccessToken;
import com.only4play.security.config.AuthErrorMsg;
import com.only4play.security.exception.CustomAuthenticationException;
import com.only4play.system.domain.admin.AdminUser;
import com.only4play.system.domain.admin.repository.AdminUserRepository;
import com.only4play.system.domain.message.verifyrecord.service.IVerifyRecordService;
import com.only4play.system.infrastructure.config.MessageProperties;
import com.only4play.system.infrastructure.config.SecurityProperties;
import com.only4play.system.infrastructure.constants.AuthConstants;
import com.only4play.system.infrastructure.constants.AuthErrorCode;
import com.only4play.system.infrastructure.security.LoginUserType;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
@Slf4j
@RequiredArgsConstructor
public class AdminSmsAuthenticationProvider extends BaseAuthenticationProvider implements AuthenticationProvider {

  private final AdminUserRepository adminUserRepository;

  private final SecurityProperties securityProperties;

  private final IVerifyRecordService verifyRecordService;

  private final MessageProperties messageProperties;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    AdminSmsToken token = (AdminSmsToken) authentication;
    if (ObjectUtils.isEmpty(token.getPhone()) || ObjectUtils.isEmpty(token.getVerifyCode())) {
      throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
    }
    Optional<AdminUser> adminUser = adminUserRepository.findByPhone(token.getPhone());
    if(!adminUser.isPresent()){
      throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
    }else {
      //验证码校验逻辑
      if (verifyRecordService.checkVerifyCode(token.getPhone(),
          messageProperties.getLoginTemplateCode(), token.getVerifyCode())) {
        Map<String,Object> jwtInfo = Maps.newHashMap();
        jwtInfo.put(AuthConstants.ID,adminUser.get().getId());
        jwtInfo.put(AuthConstants.LOGIN_USER_TYPE, LoginUserType.ADMIN_USER.getCode());
        String jwtToken = JWTUtil.createToken(jwtInfo, securityProperties.getSecret().getBytes(StandardCharsets.UTF_8));
        return new LoginSuccessToken(jwtToken, adminUser.get().getUsername());
      }else {
        throw new CustomAuthenticationException(AuthErrorCode.VERIFY_CODE_INCORRECT.getCode(),AuthErrorCode.VERIFY_CODE_INCORRECT.getName());
      }
    }
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return AdminSmsToken.class.isAssignableFrom(aClass);
  }
}
