package com.only4play.test;

import com.google.common.base.CaseFormat;

public class CaseFormatTest {

  public static void main(String[] args) {
    System.out.println(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert("User_detail"));
    System.out.println(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert("userdetail"));
    System.out.println(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert("user__detail"));
    System.out.println(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert("User__detail"));
    System.out.println(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert("user__detail"));

    System.out.println(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL).convert("user__detail"));



  }

}
