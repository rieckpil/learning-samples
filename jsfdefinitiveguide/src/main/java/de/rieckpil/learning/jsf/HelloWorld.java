package de.rieckpil.learning.jsf;

import de.rieckpil.learning.jsf.control.MessageService;
import de.rieckpil.learning.jsf.entity.Message;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class HelloWorld {

    private Message message = new Message();
    private List<Message> messageList;
    private boolean disabled = false;

    @Inject
    private MessageService messageService;

    @PostConstruct
    public void init() {
        messageList = messageService.list();
    }

    public void submit() {
        messageService.create(message);
        messageList.add(message);
    }
    public Message getMessage() {
        return message;
    }
    public List<Message> getMessages() {
        return messageList;
    }

    public boolean isDisabled() {
        return disabled;
    }
}
