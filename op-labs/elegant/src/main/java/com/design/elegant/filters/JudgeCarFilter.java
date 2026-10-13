package com.design.elegant.filters;

import com.design.elegant.charge.Car;
import com.design.elegant.charge.ChargeContext;
import com.design.elegant.pipeline.AbstractEventFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JudgeCarFilter extends AbstractEventFilter<ChargeContext> {

  @Override
  protected void handle(ChargeContext context) {
    Car car = context.getCar();
    log.info("carNO:{}",car.getCarNo());
  }
}
