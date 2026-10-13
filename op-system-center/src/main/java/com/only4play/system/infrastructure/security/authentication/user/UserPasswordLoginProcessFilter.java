package com.only4play.system.infrastructure.security.authentication.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.only4play.security.base.CustomAuthenticationFailureHandler;
import com.only4play.security.base.CustomAuthenticationSuccessHandler;
import com.only4play.security.base.PasswordLoginRequest;
import com.only4play.security.config.AuthErrorMsg;
import com.only4play.security.exception.MethodNotSupportException;
import com.only4play.system.infrastructure.security.AuthUrlEnum;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class UserPasswordLoginProcessFilter extends AbstractAuthenticationProcessingFilter {

  private final CustomAuthenticationFailureHandler failureHandler;

  private final CustomAuthenticationSuccessHandler successHandler;

  public UserPasswordLoginProcessFilter(AuthenticationManager authenticationManager,
      CustomAuthenticationFailureHandler failureHandler,
      CustomAuthenticationSuccessHandler successHandler) {
    super(AuthUrlEnum.USER_PASS.getUrl(), authenticationManager);
    this.failureHandler = failureHandler;
    this.successHandler = successHandler;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException {
    if (!HttpMethod.POST.name().equals(request.getMethod())) {
      throw new MethodNotSupportException(AuthErrorMsg.methodNotSupport.getName());
    }
    PasswordLoginRequest loginRequest = new ObjectMapper().readValue(request.getReader(),
        PasswordLoginRequest.class);
    UserPasswordToken token = new UserPasswordToken(null, loginRequest.getUsername(),
        loginRequest.getPassword());
    return this.getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException {
    successHandler.onAuthenticationSuccess(request, response, authResult);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    SecurityContextHolder.clearContext();
    failureHandler.onAuthenticationFailure(request, response, failed);
  }
}
