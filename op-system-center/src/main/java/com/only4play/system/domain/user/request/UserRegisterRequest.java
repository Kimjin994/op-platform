package com.only4play.system.domain.user.request;

import com.only4play.common.model.Request;
import lombok.Data;

@Data
public class UserRegisterRequest implements Request {

  private String phone;

  private String password;
}
