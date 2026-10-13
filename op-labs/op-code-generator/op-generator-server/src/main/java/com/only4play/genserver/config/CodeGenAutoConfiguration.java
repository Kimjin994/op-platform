package com.only4play.genserver.config;

import com.only4play.commons.engine.IGenerateEngine;
import com.only4play.commons.engine.impl.MustacheGenerateEngine;
import com.only4play.genserver.extension.ClassPathFileTemplateProvider;
import com.only4play.genserver.extension.ITemplateProvider;
import java.nio.charset.StandardCharsets;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@EnableConfigurationProperties(CodeGenServerProperties.class)
@Configuration
@ComponentScan(basePackages = {"com.only4play.genserver"})
@ConditionalOnProperty(prefix = "codegen.admin", name = "enabled", havingValue = "true")
public class CodeGenAutoConfiguration {


  /**
   * 留作一个扩展点，如果用户提供了模版那么使用用户的模版，这是封装变化的一种方式
   * @return
   */
  @Bean
  @ConditionalOnMissingBean(ITemplateProvider.class)
  public ITemplateProvider templateProvider(){
    return new ClassPathFileTemplateProvider();
  }

  /**
   * 生成器引擎扩展点，用户自己可以定制
   * @return
   */
  @Bean
  @ConditionalOnMissingBean(IGenerateEngine.class)
  public IGenerateEngine generateEngine(){
    return new MustacheGenerateEngine();
  }

  @Bean
  public SpringResourceTemplateResolver adminTemplateResolver(ApplicationContext applicationContext) {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("classpath:/META-INF/codegen-ui/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    resolver.setCacheable(true);
    resolver.setOrder(10);
    resolver.setCheckExistence(true);
    return resolver;
  }

  @Bean
  @ConditionalOnClass(DataSource.class)
  public JdbcTemplate jdbcTemplate(DataSource dataSource){
    return new JdbcTemplate(dataSource);
  }


}
