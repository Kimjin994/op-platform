package com.only4play.test;

import com.only4play.commons.engine.IGenerateEngine;
import com.only4play.commons.model.ColumnGenModel;
import com.only4play.commons.model.SourceFile;
import com.only4play.commons.model.TableGenModel;
import com.only4play.sample.IntegrationApplication;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

@SpringBootTest(classes = {IntegrationApplication.class})
public class CodeGenServiceTest {

  @Autowired
  private IGenerateEngine generateEngine;

  public static final String basePath = "src" + File.separator + "main" + File.separator + "java";

  @Test
  @SneakyThrows
  public void testGen(){
    List<TableGenModel> list = modelList();
    File file = ResourceUtils.getFile("classpath:templates/entity.mustache");
    //template 这么搞可以放在nacos 里是不是有点好玩了
    String template = new String(Files.readAllBytes(file.toPath()));
    List<SourceFile> sourceFiles = generateEngine.codeGen(list, template);


    for(SourceFile sf : sourceFiles){
      System.out.println(sf.getName());
      System.out.println(sf.getContent());
      new FileOutputStream(getDefaultGeneratePath(sf.getName())).write(sf.getContent().getBytes(
          StandardCharsets.UTF_8));
    }
  }

  /**
   * 模块拆分设计 能拆就拆
   * @return
   */
  private String getDefaultGeneratePath(String fileName){
    return System.getProperty("user.dir") + File.separator + basePath + File.separator+ fileName + ".java";
  }

  @Test
  public void testDir(){
    System.out.println(System.getProperty("user.dir"));
  }


  public List<TableGenModel> modelList(){
    List<TableGenModel> list = new ArrayList<>();
    TableGenModel tableGenModel = new TableGenModel();
    tableGenModel.setTableName("user");
    List<ColumnGenModel> columnGenModelList = new ArrayList<>();
    ColumnGenModel cm = new ColumnGenModel();
    cm.setIgnoreCreator(false);
    cm.setIgnoreVo(false);
    cm.setComment("");
    cm.setName("username");
    cm.setDataType(Types.VARCHAR);
    cm.setCustomStr("@GenClient");
    cm.setPrimaryKey(false);
    cm.setIgnoreVo(true);
    columnGenModelList.add(cm);
    tableGenModel.setColumnGenModelList(columnGenModelList);
    list.add(tableGenModel);

    return list;
  }

}
