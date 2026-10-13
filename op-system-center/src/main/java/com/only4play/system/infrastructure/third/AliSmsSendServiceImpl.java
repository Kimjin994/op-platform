package com.only4play.system.infrastructure.third;

import com.only4play.extension.executor.Extension;

@Extension(bizId = SmsBizId.ALI_SMS_BIZ)
public class AliSmsSendServiceImpl implements ISmsSendService{

  @Override
  public boolean sendSms(SmsSendModel sendModel) {
    //发送逻辑
    System.out.println("使用阿里平台发送短信");
    return true;
  }
}
