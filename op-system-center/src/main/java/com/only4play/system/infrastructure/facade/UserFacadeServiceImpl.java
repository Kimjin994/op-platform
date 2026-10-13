package com.only4play.system.infrastructure.facade;

import com.only4play.system.domain.user.UserBase;
import com.only4play.system.domain.user.repository.UserBaseRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeServiceImpl implements IUserFacadeService {

  private final UserBaseRepository userBaseRepository;

  @Override
  public Optional<UserProfile> findUserByPhone(String phone) {
    return userBaseRepository.findByPhone(phone).map(this::toProfile);
  }

  @Override
  public Optional<UserProfile> findUserById(Long id) {
    return userBaseRepository.findById(id).map(this::toProfile);
  }

  private UserProfile toProfile(UserBase user) {
    UserProfile profile = new UserProfile();
    profile.setId(user.getId());
    profile.setPhone(user.getPhone());
    return profile;
  }
}
