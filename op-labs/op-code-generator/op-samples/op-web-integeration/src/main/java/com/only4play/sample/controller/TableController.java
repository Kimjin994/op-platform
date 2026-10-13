package com.only4play.sample.controller;

import com.only4play.common.model.JsonObject;
import com.only4play.commons.model.ColumnGenModel;
import com.only4play.commons.model.TableGenModel;
import com.only4play.sample.config.GenProperties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.shardingsphere.infra.metadata.database.schema.loader.metadata.dialect.MySQLSchemaMetaDataLoader;
import org.apache.shardingsphere.infra.metadata.database.schema.loader.model.ColumnMetaData;
import org.apache.shardingsphere.infra.metadata.database.schema.loader.model.SchemaMetaData;
import org.apache.shardingsphere.infra.metadata.database.schema.loader.model.TableMetaData;
import org.assertj.core.util.Lists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/table")
@RequiredArgsConstructor
public class TableController {

  private final JdbcTemplate jdbcTemplate;

  private final DataSource dataSource;

  private final GenProperties properties;


  /**
   *
   */
  public final static String TABLE_QUERY_SQL = "select *  FROM information_schema.tables where  TABLE_SCHEMA = ?";
  /***
   * select *  FROM information_schema.tables where  TABLE_SCHEMA = 'codegen'
   * @return
   */
  @PostMapping(value = "getAllTables")
  @SneakyThrows
  public JsonObject<List<TableRowModel>> getAllTables(){
    List<TableRowModel> tables = getTables();
    return JsonObject.success(tables);
  }


  @SneakyThrows
  @PostMapping(value = "getTableModels")
  public JsonObject<SchemaMetaData> getTableInfos(){
    MySQLSchemaMetaDataLoader loader = new MySQLSchemaMetaDataLoader();
    List<TableRowModel> tableModels = getTables();
    String database = getDatabase();
    Collection<SchemaMetaData> schemaList = loader.load(dataSource, tableModels.stream().map(m -> m.getTableName()).collect(
        Collectors.toList()), database);
   return JsonObject.success(schemaList.iterator().next());
  }


  @SneakyThrows
  @PostMapping(value = "getTableList")
  public JsonObject<List<TableGenModel>> getTableList(){
    MySQLSchemaMetaDataLoader loader = new MySQLSchemaMetaDataLoader();
    List<TableRowModel> tables = getTables();
    String database = getDatabase();
    List<TableGenModel> modelList = new ArrayList<>();
    //因为sharding jdbc 里取不出来 注释，所以加一层循环,问题不大
    for(TableRowModel tr : tables){
      Collection<SchemaMetaData> schemaList = loader.load(dataSource, Lists.list(tr.getTableName()), database);
      SchemaMetaData first = schemaList.iterator().next();
      Optional<TableMetaData> table = first.getTables()
          .stream().filter(t -> Objects.equals(t.getName(), tr.getTableName())).findFirst();
      Collection<ColumnMetaData> columns = table.get().getColumns();
      List<ColumnGenModel> columnGenModels = new ArrayList<>();
      for(ColumnMetaData cmd : columns){
        ColumnGenModel cgm = new ColumnGenModel();
        cgm.setDataType(cmd.getDataType());
        cgm.setName(cmd.getName());
        cgm.setPrimaryKey(cmd.isPrimaryKey());
        columnGenModels.add(cgm);
      }
      TableGenModel tgm = new TableGenModel();
      tgm.setTableName(tr.getTableName());
      tgm.setPackageName(properties.getBasePackage());
      tgm.setTableComment(tr.getTableComment());
      tgm.setColumnGenModelList(columnGenModels);
      tgm.setCollation(tr.getTableCollation());
      tgm.setEngine(tr.getEngine());
      tgm.setDatabase(database);
      modelList.add(tgm);
    }
    return JsonObject.success(modelList);
  }

  @SneakyThrows
  private List<TableRowModel> getTables(){
    String catalog = jdbcTemplate.getDataSource().getConnection().getCatalog();
    List<TableRowModel> tables = jdbcTemplate.query(TABLE_QUERY_SQL, (rs, rowNum) -> {
      TableRowModel model = new TableRowModel();
      model.setTableName(rs.getString("TABLE_NAME"));
      model.setEngine(rs.getString("ENGINE"));
      model.setTableCollation(rs.getString("TABLE_COLLATION"));
      model.setTableComment(rs.getString("TABLE_COMMENT"));
      return model;
    }, catalog);
    return tables;
  }


  @SneakyThrows
  private String getDatabase(){
    String catalog = jdbcTemplate.getDataSource().getConnection().getCatalog();
    return catalog;
  }


  @Data
  static class TableRowModel{
    private String tableName;
    private String engine;
    private String tableCollation;
    private String tableComment;
  }

  @Data
  public static class TableResultModel {
    private String tableName;
  }

}
