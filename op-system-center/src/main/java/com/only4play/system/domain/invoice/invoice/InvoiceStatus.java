package com.only4play.system.domain.invoice.invoice;

import com.only4play.common.constants.BaseEnum;
import java.util.Optional;

public enum InvoiceStatus implements BaseEnum<InvoiceStatus> {

  MAKING(1, "开票中"),
  SUCCESS(2, "开票成功"),
  FAIL(3, "开票失败");

  private final Integer code;
  private final String name;

  InvoiceStatus(Integer code, String name) {
    this.code = code;
    this.name = name;
  }

  @Override
  public Integer getCode() {
    return code;
  }

  @Override
  public String getName() {
    return name;
  }

  public static Optional<InvoiceStatus> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(InvoiceStatus.class, code));
  }
}
