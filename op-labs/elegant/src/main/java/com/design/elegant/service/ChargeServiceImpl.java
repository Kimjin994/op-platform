package com.design.elegant.service;

import com.design.elegant.charge.ChargeContext;
import com.design.elegant.charge.ChargeModel;
import com.design.elegant.charge.ChargeRequest;
import com.design.elegant.pipeline.BizEnum;
import com.design.elegant.pipeline.FilterChainPipeline;
import com.design.elegant.pipeline.selector.FilterSelector;
import com.design.elegant.selector.ChargeFilterSelectorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeServiceImpl implements IChargeService{

  private final FilterChainPipeline chargePipeline;

  private final ChargeFilterSelectorFactory chargeFilterSelectorFactory;

  private final PluginRegistry<IChargeModelHandler,ChargeModel> pluginRegistry;

  @Override
  public void handle(ChargeRequest chargeRequest) {
    FilterSelector filterSelector = chargeFilterSelectorFactory.getFilterSelector(chargeRequest);
    ChargeContext chargeContext = new ChargeContext(BizEnum.METRIC_EVENT,filterSelector);
    chargeContext.setChargeRequest(chargeRequest);
    chargePipeline.getFilterChain().handle(chargeContext);
    ChargeModel chargeModel = chargeContext.getChargeModel();
    pluginRegistry.getPluginsFor(chargeModel)
        .forEach(handler -> handler.handleChargeModel(chargeModel));
  }
}
