package com.only4play.commons.engine.impl;

import com.only4play.commons.engine.AbstractGenerateEngine;
import com.only4play.commons.model.ColumnGenModel;
import com.only4play.commons.model.SourceFile;
import com.only4play.commons.model.TableGenModel;
import com.samskivert.mustache.Mustache;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐此实现，语法简洁方便
 */
public class MustacheGenerateEngine extends AbstractGenerateEngine {

  @Override
  public List<SourceFile> postProcessGen(List<TableGenModel> models, String template) {
    List<SourceFile> list = new ArrayList<>();
    models.forEach(t -> {
      SourceFile sf = new SourceFile();
      String content = Mustache.compiler().compile(template).execute(new Object() {
        String table = t.getClassName();
        String packageName = t.getPackageName();
        List<ColumnGenModel> columList = t.getColumnGenModelList();
      });
      sf.setContent(content);
      sf.setName(t.getClassName());
      list.add(sf);
    });
    return list;
  }
}
