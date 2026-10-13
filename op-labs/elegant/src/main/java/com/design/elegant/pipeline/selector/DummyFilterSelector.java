package com.design.elegant.pipeline.selector;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author gim
 */
public class DummyFilterSelector implements FilterSelector {

  @Override
  public boolean matchFilter(String currentFilterName) {
    return false;
  }

  @Override
  public List<String> getFilterNames() {
    return Lists.newArrayList();
  }
}
