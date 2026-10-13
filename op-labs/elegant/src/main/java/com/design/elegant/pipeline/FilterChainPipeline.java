package com.design.elegant.pipeline;

public class FilterChainPipeline<T extends EventFilter> {
  private DefaultFilterChain last;

  public FilterChainPipeline() {
  }

  public DefaultFilterChain getFilterChain() {
    return this.last;
  }

  public FilterChainPipeline addFirst(T filter) {
    DefaultFilterChain newChain = new DefaultFilterChain(this.last, filter);
    this.last = newChain;
    return this;
  }

  public FilterChainPipeline addFirst(String desc, T filter) {
    DefaultFilterChain newChain = new DefaultFilterChain(this.last, filter);
    this.last = newChain;
    return this;
  }
}
