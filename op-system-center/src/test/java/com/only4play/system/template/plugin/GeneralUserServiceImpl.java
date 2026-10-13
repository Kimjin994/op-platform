package com.only4play.system.template.plugin;

import com.only4play.system.template.fee.rule.context.OrderInfo;
import org.springframework.stereotype.Service;

@Service
public class GeneralUserServiceImpl implements IOrderService{

    @Override
    public String order(OrderInfo orderInfo) {
        System.out.println("普通用户下单了");
        return null;
    }

    @Override
    public boolean supports(OrderInfo info) {
        return info.getUserId().equals(1L);
    }
}
