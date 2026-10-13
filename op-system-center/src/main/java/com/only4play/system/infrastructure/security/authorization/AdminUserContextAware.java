package com.only4play.system.infrastructure.security.authorization;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.only4play.security.base.BaseJwtUser;
import com.only4play.security.base.extension.DummyJwtUser;
import com.only4play.security.base.extension.UserContextAware;
import com.only4play.security.config.AuthErrorMsg;
import com.only4play.security.exception.CustomAuthenticationException;
import com.only4play.system.domain.admin.service.IAdminUserService;
import com.only4play.system.domain.admin.vo.AdminUserVO;
import com.only4play.system.domain.user.UserBase;
import com.only4play.system.domain.user.repository.UserBaseRepository;
import com.only4play.system.infrastructure.config.SecurityProperties;
import com.only4play.system.infrastructure.constants.AuthConstants;
import com.only4play.system.infrastructure.security.JwtTokens;
import com.only4play.system.infrastructure.security.LoginUserType;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

/**
 * 解析token 放入用户上下文
 */
@Component
@RequiredArgsConstructor
public class AdminUserContextAware implements UserContextAware {

  private final SecurityProperties securityProperties;

  private final IAdminUserService adminUserService;

  private final AdminPermissionLoader adminPermissionLoader;

  private final UserBaseRepository userBaseRepository;

  @Override
  public BaseJwtUser processToken(String token) {
    boolean pass = JWTUtil.verify(token, securityProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    if(!pass){
      throw new CustomAuthenticationException(AuthErrorMsg.tokenIllegal.getCode(),AuthErrorMsg.tokenIllegal.getName());
    }
    JWT jwt = JWTUtil.parseToken(token);
    JwtTokens.assertNotExpired(jwt);
    Long userId = Long.valueOf(jwt.getPayload().getClaim(AuthConstants.ID).toString());
    LoginUserType type = LoginUserType.of(Integer.valueOf(jwt.getPayload().getClaim(AuthConstants.LOGIN_USER_TYPE).toString())).get();
    if(Objects.equals(LoginUserType.ADMIN_USER,type)){
        //如果是后台用户，查询用户信息，并授权
      AdminUserVO vo = adminUserService.findById(userId);
      SystemJwtUser systemJwtUser = new SystemJwtUser();
      systemJwtUser.setUsername(vo.getUsername());
      systemJwtUser.setUserId(userId);
      systemJwtUser.setAuthorities(adminPermissionLoader.load(userId));
      return systemJwtUser;
    }
    if (Objects.equals(LoginUserType.CUSTOMER, type)) {
      UserBase user = userBaseRepository.findById(userId)
          .orElseThrow(() -> new CustomAuthenticationException(AuthErrorMsg.tokenIllegal.getCode(),
              AuthErrorMsg.tokenIllegal.getName()));
      SystemJwtUser customer = new SystemJwtUser();
      customer.setUserId(userId);
      customer.setUsername(user.getPhone());
      customer.setAuthorities(AuthorityUtils.NO_AUTHORITIES);
      return customer;
    }
    return new DummyJwtUser();
  }
}
