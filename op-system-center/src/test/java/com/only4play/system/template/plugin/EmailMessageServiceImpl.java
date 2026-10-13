package com.only4play.system.template.plugin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageServiceImpl implements IMessageService{
    @Override
    public void sendMessage() {
        System.out.println("email send");
    }

    @Override
    public boolean supports(Message delimiter) {
        if(StringUtils.equals("2",delimiter.getMessageChannel())){
            return true;
        }
        return false;
    }
}
