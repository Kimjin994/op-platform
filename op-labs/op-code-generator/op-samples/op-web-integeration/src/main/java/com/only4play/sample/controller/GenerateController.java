package com.only4play.sample.controller;

import com.only4play.common.constants.CodeEnum;
import com.only4play.common.model.JsonObject;
import com.only4play.commons.engine.IGenerateEngine;
import com.only4play.commons.model.SourceFile;
import com.only4play.commons.model.TableGenModel;
import com.only4play.sample.config.GenProperties;
import com.only4play.sample.utils.CodeUtils;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/generate")
@RequiredArgsConstructor
public class GenerateController {

  private final IGenerateEngine generateEngine;
  private final GenProperties properties;

  @SneakyThrows
  @PostMapping(value = "doGenerate")
  public JsonObject<String> codeGen(@RequestBody GenerateReq req){
    File file = ResourceUtils.getFile("classpath:templates/entity.mustache");
    String template = new String(Files.readAllBytes(file.toPath()));
    List<SourceFile> sourceFiles = generateEngine.codeGen(req.getModelList(), template);
    CodeUtils.genFile(sourceFiles,properties.getGenBasePath());
    return JsonObject.success(CodeEnum.Success.getName());
  }


  @Data
  static class GenerateReq{
    private List<TableGenModel> modelList;
  }



}
