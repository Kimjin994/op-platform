package com.only4play.system.template.plugin;

import com.only4play.system.template.fee.rule.context.OrderInfo;
import org.springframework.stereotype.Service;

@Service
public class VipOrderServiceImpl implements IOrderService{
    @Override
    public String order(OrderInfo orderInfo) {
        System.out.println("vip 下单了");
        return null;
    }

    @Override
    public boolean supports(OrderInfo orderInfo) {
        return orderInfo.getUserId().equals(2L);
    }
}
