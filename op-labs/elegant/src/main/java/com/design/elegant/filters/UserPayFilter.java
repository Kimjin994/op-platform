package com.design.elegant.filters;

import com.design.elegant.charge.ChargeContext;
import com.design.elegant.pipeline.AbstractEventFilter;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;

@Slf4j
public class UserPayFilter extends AbstractEventFilter<ChargeContext> {

  @Override
  protected void handle(ChargeContext context) {
      log.info("支付判断逻辑");
  }
}
