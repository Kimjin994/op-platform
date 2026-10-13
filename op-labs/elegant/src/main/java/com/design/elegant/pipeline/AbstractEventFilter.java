package com.design.elegant.pipeline;


import com.design.elegant.pipeline.context.EventContext;

/**
 * @author gim 模板方法
 */
public abstract class AbstractEventFilter<T extends EventContext> implements EventFilter<T> {

  @Override
  public void doFilter(T context, EventFilterChain chain) {
    if (context.getFilterSelector().matchFilter(this.getClass().getSimpleName())) {
      handle(context);
    }
    if (context.continueChain()) {
      chain.fireNext(context);
    }
  }

  protected abstract void handle(T context);
}
