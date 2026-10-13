package com.only4play.genserver.utils;

import com.only4play.common.constants.CodeEnum;
import com.only4play.common.exception.BusinessException;
import com.only4play.commons.model.SourceFile;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CodeUtils {

  private CodeUtils() {
  }

  public static void genFile(List<SourceFile> sourceFiles, String basePath) {
    for (SourceFile sf : sourceFiles) {
      try {
        new FileOutputStream(getFilePath(sf, basePath)).write(sf.getContent().getBytes(
            StandardCharsets.UTF_8));
      } catch (Exception e) {
        throw new BusinessException(CodeEnum.ParamSetIllegal);
      }
    }
  }

  public static String getFilePath(SourceFile sourceFile, String basePath) {
     String packagePath = basePath +File.separator + sourceFile.getName().toLowerCase();
    File file = new File(packagePath);
    if (!file.exists() && !file.isDirectory()) {
      file.mkdirs();
    }
    String fileName = packagePath  + File.separator + sourceFile.getName() + "." + sourceFile.getPostFix();
    File genFile = new File(fileName);
    genFile.delete();
    return fileName;
  }

}
