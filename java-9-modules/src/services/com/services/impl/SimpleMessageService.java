package com.services.impl;

import com.services.api.MessageService;

public class SimpleMessageService implements MessageService {
    public String generateMessage() {
        return "Simple impl message";
    }
}