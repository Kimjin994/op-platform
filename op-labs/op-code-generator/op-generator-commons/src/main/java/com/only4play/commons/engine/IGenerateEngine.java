package com.only4play.commons.engine;

import com.only4play.commons.model.SourceFile;
import com.only4play.commons.model.TableGenModel;
import java.util.List;

/**
 * 当然了代码生成器 可以做一些额外的配置
 */
public interface IGenerateEngine {

  /**
   * 基于模板和模型生成代码字符串
   * @param models
   * @param template  参数用String 更加合理 ，传路径过于局限。职责单一设计，到字符串有很多种读取渠道
   *                  比如nacos,文件读取，前端传输
   */
  List<SourceFile> codeGen(List<TableGenModel> models,String template);




}
