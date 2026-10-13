package com.only4play.system.template.plugin;

import com.only4play.system.template.fee.rule.context.OrderInfo;
import org.springframework.plugin.core.Plugin;

public interface IOrderService extends Plugin<OrderInfo> {

    String order(OrderInfo orderInfo);
}
