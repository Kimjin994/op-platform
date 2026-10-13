package com.only4play.system.domain.message.messagerecord.request;

import com.only4play.common.model.Request;
import lombok.Data;

@Data
public class SendVerifyCodeRequest implements Request {

  private String account;
}
