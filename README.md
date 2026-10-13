# op-platform

B 站 UP「zl无悔青春」领域服务实战课程（Gitee [only4playgroup/op-system-center](https://gitee.com/only4playgroup/op-system-center)）的代码整理版。原仓库是按课次堆放的快照和 zip，这里按模块归集成一个可构建、可启动的 Maven 多模块工程。

## 目录

```
op-platform
├── op-parent                  版本管理（替代原作者私服上的 op-bom 3.0.1）
├── op-framework               通用基础包
│   ├── op-commons             通用注解、异常、枚举、分页模型
│   ├── op-order-commons       订单/支付通用模型（PayItem 等）
│   ├── op-codegen-apt         注解处理器：@GenController/@GenService... 生成 CRUD 代码
│   ├── op-jpa-spring-boot-starter        BaseJpaAggregate、EntityOperations
│   ├── op-security-spring-boot-starter   JWT + 多种登录方式的骨架
│   ├── op-extension-spring-boot-starter  扩展点（按 bizId 路由实现）
│   ├── op-oss-spring-boot-starter        S3 协议对象存储
│   └── op-mybatis-spring-boot-starter    MyBatis-Plus 版的基础设施
├── op-system-center           主应用：交易、资产、模板、权限、用户、发票、消息
└── op-labs                    课上的练习工程（-Plabs 时参与构建）
    ├── op-examsrv             考试/用例服务（mock 服务那一课）
    ├── op-imserver            Netty + WebFlux 的 IM
    ├── op-order-center-job    ElasticJob + Spring Batch 订单任务
    ├── op-code-generator      代码生成器 2.0（含 UI 和样例）
    ├── op-codegen-test        op-codegen-apt 的用法样例
    ├── op-archetypes          服务脚手架 archetype
    ├── elegant                管道/过滤器式统一算费
    ├── op-geek-tools          小工具
    ├── op-mybatis-demo        mybatis starter 用法
    ├── flinktest              Flink 作业（单独构建）
    └── command-tool           Spring Shell 代码生成（JDK 17 + Boot 3，单独构建）
```

主应用取自原仓库 `op-system-center/` 子目录（2022-11，最全的一版）；原仓库根目录的 `src` 和 `op-system-center 2~7` 是更早的课次快照，没有收进来。

## 构建

需要 JDK 8、Maven 3.6+。

```bash
mvn install -DskipTests            # 框架 + 主应用
mvn install -DskipTests -Plabs     # 连同练习工程
```

## 运行主应用

默认 `local` profile：内存 H2，不连 Nacos，连接本机 ES（`127.0.0.1:9200`，账号 `elastic` / `elastic`）。

```bash
java -jar op-system-center/target/system-center.jar
```

- 接口文档：http://localhost:8889/swagger-ui.html
- 健康检查：http://localhost:8889/actuator/health
- 预置管理员：`POST /auth/admin/pass`，body `{"username":"13800000000","password":"123456"}`，返回的 token 放到请求头 `token` 里
- 分页接口的 `page` 从 1 开始

`local` 启动时会在 ES 里创建订单、回执、发票索引。本机没起 ES 时加 `ES_ENABLED=false`。要改用 MySQL，切到 `dev`。

### dev profile

连本机 MySQL（库 `system-center` 不存在会自动创建）和 ES 7.15：

```bash
java -jar op-system-center/target/system-center.jar --spring.profiles.active=dev
```

| 环境变量 | 默认值 |
|---|---|
| `MYSQL_HOST` / `MYSQL_PORT` | `127.0.0.1` / `3306` |
| `MYSQL_USER` / `MYSQL_PASSWORD` | `root` / 空 |
| `ES_ENABLED` | `true`，设为 `false` 时不建索引、不做 ES 健康检查 |
| `ES_NODE` / `ES_USER` / `ES_PASSWORD` | `127.0.0.1:9200` / `elastic` / `elastic` |
| `NACOS_ENABLED` / `NACOS_ADDR` | `false` / `127.0.0.1:8848` |

没装 ES 时加 `ES_ENABLED=false` 启动。`dev` 不自动预置数据，管理员可以用 `op-system-center/src/main/resources/db/local-data.sql` 里的那条 INSERT 手工插入。

## 相对原代码的改动

- 版本统一为 `1.0.0-SNAPSHOT`，内部依赖由 `op-parent` 管理；Spring Boot 2.6.3、Spring Cloud 2021.0.1、Spring Cloud Alibaba 2021.0.1.0 与原 readme 一致，fastjson 升到 1.2.83。
- QueryDSL 改为编译期注解处理器（替代 `apt-maven-plugin`），和代码生成器同一轮生成。
- ES 文档关闭了启动时自动建索引，改由 `ElasticsearchIndexInitializer` 在 `es.enabled=true` 时创建。
- 配置原本放在 Nacos 上，拆成 `application.yml` + `application-local.yml` + `application-dev.yml`。
- 测试代码挪到标准的 `src/test/java`。
- 练习工程里的 `op-code-generator/op-codegen-apt` 改名为 `op-codegen-apt-v2`，避免和框架层坐标冲突。

## 接口权限码

业务接口使用 `@PreAuthorize("@perm.has('模块:资源:操作')")`。登录、`/public/**`、`/auth/**`、Swagger 和健康检查不校验功能权限。

角色编码 `ADMIN` 会得到 `ROLE_ADMIN`，接口校验直接通过。`local` 预置管理员 `13800000000` 在 `db/local-data.sql` 里绑定该角色。

完整权限码写在同一份 SQL 的 `resource.code` 中。操作名包括 `create`、`update`、`valid`、`invalid`、`view`、`list`，以及 `complete`、`cancel`、`revise`、`exchange`、`abandon`、`confirm`、`in`、`out`、`transfer`、`grant`、`gen`、`mock`、`verify`、`send`。

令牌有效期为 `jwt.expireMinutes`，默认 120 分钟。
