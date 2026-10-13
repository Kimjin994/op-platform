package com.design.elegant.selector;

import com.design.elegant.charge.ChargeRequest;
import com.design.elegant.pipeline.selector.FilterSelector;

public interface ChargeFilterSelectorFactory {


  FilterSelector  getFilterSelector(ChargeRequest request);


}
