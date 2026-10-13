package com.design.elegant.pipeline;


import com.design.elegant.pipeline.context.EventContext;

/**
 * @author gim
 */
public interface EventFilter<T extends EventContext> {

  /**
   * 过滤逻辑封装点
   *
   * @param context
   * @param chain
   */
  void doFilter(T context, EventFilterChain chain);


}
