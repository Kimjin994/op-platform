package com.design.elegant.pipeline.context;


import com.design.elegant.pipeline.BizEnum;
import com.design.elegant.pipeline.selector.FilterSelector;

/**
 * @author gim
 */
public interface EventContext{

  /**
   * 获取业务编码
   * @return
   */
  BizEnum getBizCode();

  /**
   * 获取过滤器选择器
   * @return
   */
  FilterSelector getFilterSelector();

  /**
   * 是否继续链
   * @return
   */
  boolean continueChain();



}
