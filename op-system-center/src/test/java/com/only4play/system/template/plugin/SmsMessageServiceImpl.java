package com.only4play.system.template.plugin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SmsMessageServiceImpl implements IMessageService{
    @Override
    public boolean supports(Message delimiter) {
        return StringUtils.equals("1",delimiter.getMessageChannel());
    }

    @Override
    public void sendMessage() {
        System.out.println("sms send");
    }
}
