package com.design.elegant.filters;

import com.design.elegant.charge.ChargeContext;
import com.design.elegant.pipeline.AbstractEventFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LogSaveFilter extends AbstractEventFilter<ChargeContext> {

  @Override
  protected void handle(ChargeContext context) {
    log.info("请求存储，发送到mq");
  }
}
