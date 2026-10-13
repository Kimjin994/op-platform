package com.only4play.genserver.controller;

import com.only4play.common.constants.CodeEnum;
import com.only4play.common.model.JsonObject;
import com.only4play.commons.engine.IGenerateEngine;
import com.only4play.commons.model.SourceFile;
import com.only4play.commons.model.TableGenModel;
import com.only4play.genserver.config.GenProperties;
import com.only4play.genserver.constants.ErrorCode;
import com.only4play.genserver.extension.ITemplateProvider;
import com.only4play.genserver.utils.CodeUtils;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;
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
  private final ITemplateProvider templateProvider;

  @SneakyThrows
  @PostMapping(value = "doGenerate")
  public JsonObject<String> codeGen(@RequestBody GenerateReq req){
    if(!StringUtils.hasText(properties.getBasePackage()) || !StringUtils.hasText(properties.getGenBasePath())){
      return JsonObject.fail(ErrorCode.CONFIG_WRONG.getName());
    }
    List<SourceFile> sourceFiles = generateEngine.codeGen(req.getModelList(), templateProvider.getTemplateStr());
    CodeUtils.genFile(sourceFiles,properties.getGenBasePath());
    return JsonObject.success(CodeEnum.Success.getName());
  }


  @Data
  static class GenerateReq{
    private List<TableGenModel> modelList;
  }



}
