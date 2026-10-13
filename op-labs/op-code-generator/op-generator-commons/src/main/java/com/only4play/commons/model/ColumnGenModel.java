package com.only4play.commons.model;

import lombok.Data;

/**
 * 定制化Column 模型
 */
@Data
public class ColumnGenModel extends BaseColumnGenModel{

  /**
   * 忽略创建
   */
  private boolean ignoreCreator;

  /**
   * 忽略更新
   */
  private boolean ignoreUpdater;

  /**
   * 是否是查询条件
   */
  private boolean queryItem;

  /**
   * 忽略Vo
   */
  private boolean ignoreVo;

  /**
   * 校验正则表达式
   */
  private String regex;

  /**
   * 校验提示信息
   */
  private String message;

  /**
   * 自定义字符串 ，可以扩展任何注解
   */
  private String customStr = "";

  /**
   * 以下部分通过程序处理后供生成使用
   */

  /**
   * Java 类型
   */
  private String javaType;

  /**
   * Java 类型全称
   */
  private String javaFullType;

  /**
   * 属性名称
   */
  private String fieldName;


}
