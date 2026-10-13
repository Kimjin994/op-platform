package com.only4play.sample;

import static java.util.Arrays.asList;

import com.only4play.sample.filter.HomepageForwardingFilter;
import com.only4play.sample.filter.HomepageForwardingFilterConfig;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@SpringBootApplication
public class IntegrationApplication {

  private static final List<String> DEFAULT_UI_ROUTES = asList("/form/**", "/system/**", "/component/**", "/login", "/workspace/**");

  private static final List<String> DEFAULT_UI_ROUTE_EXCLUDES = asList("/user/**");

  @Autowired
  private ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(IntegrationApplication.class,args);
  }

  @Bean
  public SpringResourceTemplateResolver adminTemplateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(this.applicationContext);
    resolver.setPrefix("classpath:/META-INF/codegen-ui/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    resolver.setCacheable(true);
    resolver.setOrder(10);
    resolver.setCheckExistence(true);
    return resolver;
  }

//  @Bean
//  public HomepageForwardingFilterConfig homepageForwardingFilterConfig() throws IOException {
//    String homepage = ("/");
//    return new HomepageForwardingFilterConfig(homepage, DEFAULT_UI_ROUTES, DEFAULT_UI_ROUTE_EXCLUDES);
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public HomepageForwardingFilter homepageForwardFilter(
//      HomepageForwardingFilterConfig homepageForwardingFilterConfig) throws IOException {
//    return new HomepageForwardingFilter(
//        homepageForwardingFilterConfig);
//  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource){
    return new JdbcTemplate(dataSource);
  }


}
