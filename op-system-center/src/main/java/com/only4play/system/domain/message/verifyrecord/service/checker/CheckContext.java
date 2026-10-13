package com.only4play.system.domain.message.verifyrecord.service.checker;

import com.only4play.common.annotation.FieldDesc;
import lombok.Data;

@Data
public class CheckContext {

  @FieldDesc(name = "账号")
  private String account;

  @FieldDesc(name = "发送间隔")
  private Integer sendInterval;

  @FieldDesc(name = "发送模板")
  private String templateCode;

  @FieldDesc(name = "发送最大次数")
  private Integer sendMaxTimes;


}
