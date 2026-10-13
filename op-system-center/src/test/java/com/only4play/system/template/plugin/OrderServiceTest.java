package com.only4play.system.template.plugin;

import com.only4play.system.template.fee.rule.context.OrderInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnablePluginRegistries(value = {IOrderService.class,IMessageService.class})
public class OrderServiceTest {

    @Autowired
    private PluginRegistry<IOrderService, OrderInfo> registry;

    @Autowired
    private PluginRegistry<IMessageService, Message> registry2;


    @Test
    public void testOrderService(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(2L);
        registry.getPluginFor(orderInfo).ifPresent(
                f -> f.order(orderInfo)
        );
    }


    @Test
    public void testSendMessage(){
        Message message = new Message();
        message.setMessageChannel("2");
        registry2.getPluginFor(message).ifPresent(
                f -> f.sendMessage()
        );

    }





}
