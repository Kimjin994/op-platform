package com.only4play.system.infrastructure.third;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class SmsSendModel {

  private List<String> phones;

  private Map<String,String> params;

  private String templateCode;

}
