package com.services.api;

import com.services.api.MessageService;
import com.services.impl.SimpleMessageService;

public class MessageServiceFactory {
    public static MessageService createMessageService() {
        return new SimpleMessageService();
    }
}