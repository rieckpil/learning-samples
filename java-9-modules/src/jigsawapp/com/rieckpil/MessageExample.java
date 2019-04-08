package com.rieckpil;

import com.services.api.MessageService;
import com.services.api.MessageServiceFactory;

public class MessageExample {

    public static void main(String[] args) {
        final MessageService service = MessageServiceFactory.createMessageService();
        System.out.println("Output: " + service.generateMessage());        
    }

}