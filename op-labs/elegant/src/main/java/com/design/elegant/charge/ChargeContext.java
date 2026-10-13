package com.design.elegant.charge;

import com.design.elegant.pipeline.BizEnum;
import com.design.elegant.pipeline.context.AbstractEventContext;
import com.design.elegant.pipeline.selector.FilterSelector;
import lombok.Getter;
import lombok.Setter;


public class ChargeContext extends AbstractEventContext {

  @Setter
  @Getter
  private ChargeRequest chargeRequest;

  @Setter
  @Getter
  private Car car;

  @Setter
  @Getter
  private ChargeModel chargeModel;

  public ChargeContext(BizEnum bizEnum,
      FilterSelector selector) {
    super(bizEnum, selector);
  }

  @Override
  public boolean continueChain() {
    return true;
  }
}
