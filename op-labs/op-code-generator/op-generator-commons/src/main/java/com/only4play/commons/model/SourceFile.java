package com.only4play.commons.model;

import lombok.Data;

@Data
public class SourceFile {

  /**
   * 文件名
   */
  private String name;

  private String content;

  private String postFix = "java";

}
