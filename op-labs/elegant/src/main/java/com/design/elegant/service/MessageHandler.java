package com.design.elegant.service;

import com.design.elegant.charge.ChargeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageHandler implements IChargeModelHandler{

  @Override
  public void handleChargeModel(ChargeModel chargeModel) {
    log.info("消息发送");
  }

  @Override
  public boolean supports(ChargeModel model) {
    return true;
  }
}
