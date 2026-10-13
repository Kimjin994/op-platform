package com.only4play.genserver.extension;

import java.io.File;
import java.nio.file.Files;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

public class ClassPathFileTemplateProvider implements ITemplateProvider{

  @Override
  @SneakyThrows
  public String getTemplateStr() {
    File file = ResourceUtils.getFile("classpath:templates/entity.mustache");
    return new String(Files.readAllBytes(file.toPath()));
  }
}
