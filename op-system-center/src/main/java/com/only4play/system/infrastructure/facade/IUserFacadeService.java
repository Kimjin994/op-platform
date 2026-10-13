package com.only4play.system.infrastructure.facade;

import java.util.Optional;

public interface IUserFacadeService {

  Optional<UserProfile> findUserByPhone(String phone);

  Optional<UserProfile> findUserById(Long id);
}
