package com.design.elegant.pipeline;

import com.design.elegant.pipeline.context.EventContext;
import java.util.Objects;

/**
 * @author gim
 */
public class DefaultFilterChain<T extends EventContext> implements EventFilterChain<T> {

  private EventFilterChain<T> next;
  private EventFilter<T> filter;

  public DefaultFilterChain(EventFilterChain chain, EventFilter filter) {
    this.next = chain;
    this.filter = filter;
  }

  @Override
  public void handle(T context) {
    filter.doFilter(context, this);
  }

  @Override
  public void fireNext(T ctx) {
    EventFilterChain nextChain = this.next;
    if (Objects.nonNull(nextChain)) {
      nextChain.handle(ctx);
    }
  }
}
