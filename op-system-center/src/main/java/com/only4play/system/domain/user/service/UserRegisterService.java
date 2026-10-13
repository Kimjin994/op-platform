package com.only4play.system.domain.user.service;

import cn.hutool.core.util.IdUtil;
import com.only4play.common.constants.CodeEnum;
import com.only4play.common.exception.BusinessException;
import com.only4play.system.domain.user.AccountType;
import com.only4play.system.domain.user.creator.UserAccountCreator;
import com.only4play.system.domain.user.creator.UserBaseCreator;
import com.only4play.system.domain.user.repository.UserBaseRepository;
import com.only4play.system.infrastructure.constants.AuthErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

  private final UserBaseRepository userBaseRepository;

  private final IUserBaseService userBaseService;

  private final IUserAccountService userAccountService;

  @Transactional
  public Long register(String phone, String password) {
    if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
      throw new BusinessException(CodeEnum.ParamSetIllegal);
    }
    if (userBaseRepository.findByPhone(phone).isPresent()) {
      throw new BusinessException(AuthErrorCode.PHONE_EXIST);
    }
    UserBaseCreator base = new UserBaseCreator();
    base.setPhone(phone);
    base.setUuid(IdUtil.fastSimpleUUID());
    Long userId = userBaseService.createUserBase(base);
    UserAccountCreator account = new UserAccountCreator();
    account.setUserId(userId);
    account.setAccountNo(phone);
    account.setPassword(password);
    account.setAccountType(AccountType.PERSONAL);
    userAccountService.createUserAccount(account);
    return userId;
  }
}
