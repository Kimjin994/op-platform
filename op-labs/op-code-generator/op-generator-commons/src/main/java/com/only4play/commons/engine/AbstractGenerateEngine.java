package com.only4play.commons.engine;

import com.google.common.base.CaseFormat;
import com.only4play.commons.converter.JavaTypes;
import com.only4play.commons.model.SourceFile;
import com.only4play.commons.model.TableGenModel;
import java.util.List;

public abstract class AbstractGenerateEngine implements IGenerateEngine{


  @Override
  public List<SourceFile> codeGen(List<TableGenModel> models, String template) {
    models.forEach(m -> {
      m.setPackageName(m.getPackageName() + "." + m.getTableName().toLowerCase());
      m.setClassName( CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert(m.getTableName()));
      m.getColumnGenModelList()
          .forEach(c -> {
            c.setFieldName(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL).convert(c.getName()));
            c.setJavaType(JavaTypes.ofSqlCode(c.getDataType()).getJavaType());
            c.setJavaFullType(JavaTypes.ofSqlCode(c.getDataType()).getFullJavaType());
          });
    });
    return postProcessGen(models,template);
  }

  protected abstract List<SourceFile> postProcessGen(List<TableGenModel> models, String template);
}
