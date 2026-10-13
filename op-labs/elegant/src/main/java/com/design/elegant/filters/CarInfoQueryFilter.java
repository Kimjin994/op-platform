package com.design.elegant.filters;

import com.design.elegant.charge.Car;
import com.design.elegant.charge.ChargeContext;
import com.design.elegant.charge.ChargeModel;
import com.design.elegant.charge.ChargeRequest;
import com.design.elegant.pipeline.AbstractEventFilter;
import com.design.elegant.service.IFacadeService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CarInfoQueryFilter extends AbstractEventFilter<ChargeContext> {

  //filter 里面 查询缓存，使用消息 ，调用其他服务
  private final IFacadeService facadeService;

  @Override
  protected void handle(ChargeContext context) {
    if(Objects.isNull(context.getCar())){
      ChargeRequest chargeRequest = context.getChargeRequest();
      String carNo = chargeRequest.getCarNo();
      Car car = facadeService.getCarInfoByCarNO(carNo);
      context.setCar(car);
      ChargeModel chargeModel = new ChargeModel();
      context.setChargeModel(chargeModel);
    }
    log.info("查询车辆信息并且放入上下文中");

  }
}
