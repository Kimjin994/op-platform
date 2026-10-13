package com.design.elegant.config;

import com.design.elegant.filters.CarInfoQueryFilter;
import com.design.elegant.filters.JudgeCarFilter;
import com.design.elegant.filters.LogSaveFilter;
import com.design.elegant.filters.UserPayFilter;
import com.design.elegant.pipeline.FilterChainPipeline;
import com.design.elegant.service.IFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChargePipelineConfig {

  @Autowired
  private  IFacadeService facadeService;

  @Bean
  public FilterChainPipeline chargePipeline(){
    FilterChainPipeline filterChainPipeline = new FilterChainPipeline();
    filterChainPipeline.addFirst("用户逻辑",userPayFilter());
    filterChainPipeline.addFirst("车辆信息判断",judgeCarFilter());
    filterChainPipeline.addFirst("车辆信息查询", carInfoQueryFilter());
    filterChainPipeline.addFirst("log存储",logSaveFilter());
    return filterChainPipeline;
  }

  @Bean
  public CarInfoQueryFilter carInfoQueryFilter(){
    return new CarInfoQueryFilter(facadeService);
  }

  @Bean
  public JudgeCarFilter judgeCarFilter(){
    return new JudgeCarFilter();
  }

  @Bean
  public LogSaveFilter logSaveFilter(){
    return new LogSaveFilter();
  }

  @Bean
  public UserPayFilter userPayFilter(){
    return new UserPayFilter();
  }


}
