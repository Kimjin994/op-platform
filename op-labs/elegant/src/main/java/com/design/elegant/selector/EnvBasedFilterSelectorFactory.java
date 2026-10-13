package com.design.elegant.selector;

import com.design.elegant.charge.ChargeRequest;
import com.design.elegant.config.ChargeFilterConfigProperties;
import com.design.elegant.pipeline.selector.FilterSelector;
import com.design.elegant.pipeline.selector.LocalListBasedFilterSelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnvBasedFilterSelectorFactory implements ChargeFilterSelectorFactory{

  private final ChargeFilterConfigProperties properties;

  @Override
  public FilterSelector getFilterSelector(ChargeRequest request) {
    String bizCode = request.getBizCode();
    if("YW1".equals(bizCode)){
      List<String> filterNames = properties.getConfigs()
          .getOrDefault("YW1", Collections.unmodifiableList(new ArrayList<>()));
      return new LocalListBasedFilterSelector(filterNames);
    }
    return null;
  }
}
