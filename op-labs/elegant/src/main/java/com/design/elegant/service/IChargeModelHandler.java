package com.design.elegant.service;

import com.design.elegant.charge.ChargeModel;
import org.springframework.plugin.core.Plugin;

public interface IChargeModelHandler extends Plugin<ChargeModel> {

  void handleChargeModel(ChargeModel chargeModel);

}
