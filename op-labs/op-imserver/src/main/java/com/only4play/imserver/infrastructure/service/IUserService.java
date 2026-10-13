package com.only4play.imserver.infrastructure.service;

import com.only4play.imserver.infrastructure.model.UserContext;
import java.util.Optional;

public interface IUserService {

  Optional<UserContext> parseToken(String token);

}
