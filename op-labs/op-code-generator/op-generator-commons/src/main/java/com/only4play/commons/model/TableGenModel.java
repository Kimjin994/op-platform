package com.only4play.commons.model;

import java.util.List;
import lombok.Data;

@Data
public class TableGenModel {

  /**
   * 表名
   */
  private String tableName;

  /**
   * 生成类名
   */
  private String className;

  /**
   * 列明细列表
   */
  private List<ColumnGenModel> columnGenModelList;

  /**
   * 表注释
   */
  private String tableComment;

  /**
   * 表引擎
   */

  private String engine;

  /**
   * 编码
   */
  private String collation;

  /**
   * 数据库
   */
  private String database;

  /**
   * 类的包名
   */
  private String packageName = "";
}
