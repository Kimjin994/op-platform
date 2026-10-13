package com.design.elegant;

import com.design.elegant.charge.ChargeRequest;
import com.design.elegant.service.IChargeService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElegantApplicationTests {

  @Autowired
  private IChargeService chargeService;

  @Test
  void contextLoads() {
  }


  @Test
  public void testCharge(){
    ChargeRequest request = new ChargeRequest();
    request.setBizCode("YW1");
    request.setCarNo("2343");
    chargeService.handle(request);
  }


}
