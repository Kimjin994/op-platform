package com.design.elegant.pipeline.selector;


import com.design.elegant.pipeline.BizEnum;

/**
 * @author gim
 */
public interface BizAware {

  /**
   * 获取当前业务编码
   * @return
   */
  BizEnum getBizCode();
}
