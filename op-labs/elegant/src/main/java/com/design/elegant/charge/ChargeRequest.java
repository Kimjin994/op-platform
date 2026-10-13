package com.design.elegant.charge;

import lombok.Data;

@Data
public class ChargeRequest {

  private String bizCode;

  private Long userId;

  private Long stakeId;

  private String carNo;

  private String extra;

}
