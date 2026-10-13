package com.only4play.system.infrastructure.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
    return new JPAQueryFactory(entityManager);
  }

}
