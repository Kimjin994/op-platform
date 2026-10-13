package com.only4play.commons.model;

import lombok.Data;

@Data
public class BaseColumnGenModel {

  /**
   * 是否是主键
   */
  private boolean primaryKey;

  /**
   * 数据类型
   */
  private Integer dataType;

  /**
   * 字段名称
   */
  private String name;

  /**
   * 注释
   */
  private String comment = "";

}
