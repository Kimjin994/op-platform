package com.only4play.system.domain.user.request;

import com.only4play.common.model.Request;
import lombok.Data;

@Data
public class ChangePasswordRequest implements Request {

  private Long id;

  private String oldPassword;

  private String newPassword;
}
