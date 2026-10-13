package com.only4play.commons.converter;

import java.sql.Types;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;

/**
 * 规范，不用日期类型
 * 这里就是把MySQL 中的type 跟Java 类型做好映射就行了，添加常见的，如果需要就添加配置就好了
 */
public enum JavaTypes {

  DECIMAL(Types.DECIMAL,"BigDecimal","java.math.BigDecimal"),
  BINARY(Types.BINARY,"byte[]",""),
  TINYINT(Types.TINYINT,"Integer","java.lang.Integer"),
  CHAR(Types.CHAR,"String", "java.lang.String"),
  VARCHAR(Types.VARCHAR,"String", "java.lang.String"),
  LONG(Types.BIGINT,"Long","java.lang.Long"),
  INT(Types.INTEGER,"Integer","java.lang.Integer"),
  FLOAT(Types.FLOAT,"BigDecimal","java.math.BigDecimal"),
  DOUBLE(Types.DOUBLE,"BigDecimal","java.math.BigDecimal"),
  LONG_VARCHAR(Types.LONGVARCHAR,"String","java.lang.String"),
  TIMESTAMP(Types.TIMESTAMP,"LocalDateTime", "java.time.LocalDateTime");
  ;

  @Getter
  private final Integer sqlTypeCode;

  @Getter
  private final String javaType;

  @Getter
  private final String fullJavaType;

  JavaTypes(Integer sqlTypeCode,String javaType,String fullJavaType){
    this.sqlTypeCode = sqlTypeCode;
    this.javaType = javaType;
    this.fullJavaType = fullJavaType;
  }


  public static JavaTypes ofSqlCode(Integer code){
    Optional<JavaTypes> first = EnumSet.allOf(JavaTypes.class)
        .stream().filter(t -> Objects.equals(t.getSqlTypeCode(), code))
        .findFirst();
    return first.orElse(JavaTypes.CHAR);
  }





}
