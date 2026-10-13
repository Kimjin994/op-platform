package com.only4play.imserver.infrastructure.service;

import com.only4play.imserver.infrastructure.model.UserContext;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

  @Override
  public Optional<UserContext> parseToken(String token) {
    if("6666".equals(token)){
      UserContext userContext = new UserContext();
      userContext.setUserId(666L);
      userContext.setUsername("李四");
      return Optional.of(userContext);
    }else {
      return Optional.empty();
    }
  }
}
