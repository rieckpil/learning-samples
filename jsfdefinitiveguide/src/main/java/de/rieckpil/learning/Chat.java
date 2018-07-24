package de.rieckpil.learning;

import javax.enterprise.context.RequestScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Chat {

    @Inject
    @Push(channel = "test")
    private PushContext test;

    @Inject
    @Push(channel = "chat")
    private PushContext chat;

    public void submit() {
        System.out.println("websocket triggered");
        test.send("Hello World!");
    }

    public void submitChatMsg() {
        System.out.println("websocket triggered for chatmsg");
        test.send("Hello World!", 43);
    }
}
