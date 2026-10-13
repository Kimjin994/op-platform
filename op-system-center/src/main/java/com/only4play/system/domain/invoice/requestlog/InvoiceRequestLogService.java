package com.only4play.system.domain.invoice.requestlog;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceRequestLogService {

  public static final String REGISTER = "REGISTER";
  public static final String EXCHANGE = "EXCHANGE";

  private final InvoiceRequestLogRepository invoiceRequestLogRepository;

  public void save(String requestType, String bizNo, Object body) {
    InvoiceRequestLog log = new InvoiceRequestLog();
    log.setRequestType(requestType);
    log.setBizNo(bizNo);
    log.setBody(JSON.toJSONString(body));
    invoiceRequestLogRepository.save(log);
  }
}
