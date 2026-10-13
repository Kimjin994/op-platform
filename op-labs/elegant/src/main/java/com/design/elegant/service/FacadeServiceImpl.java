package com.design.elegant.service;

import com.design.elegant.charge.Car;
import org.springframework.stereotype.Service;

@Service
public class FacadeServiceImpl implements IFacadeService{

  @Override
  public Car getCarInfoByCarNO(String carNo) {
    Car car = new Car();
    car.setCarNo("32343");
    return car;
  }
}
