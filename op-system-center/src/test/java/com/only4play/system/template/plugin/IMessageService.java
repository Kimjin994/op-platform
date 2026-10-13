package com.only4play.system.template.plugin;

import org.springframework.plugin.core.Plugin;

public interface IMessageService extends Plugin<Message> {

    public void sendMessage();
}
