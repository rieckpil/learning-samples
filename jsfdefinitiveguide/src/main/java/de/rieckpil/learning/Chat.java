package de.rieckpil.learning;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.WebsocketEvent;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class Chat {

    @Inject
    @Push(channel = "chat")
    private PushContext chat;

    private Map<Serializable, AtomicInteger> users;

    @PostConstruct
    public void init() {
        users = new ConcurrentHashMap<>();
    }

    public void onopen(@Observes @WebsocketEvent.Opened WebsocketEvent event) {
        if ("chat".equals(event.getChannel())) {
            getCounter(event.getUser()).incrementAndGet();
        }
    }

    public void onclose(@Observes @WebsocketEvent.Closed WebsocketEvent event) {
        if ("chat".equals(event.getChannel())) {
            getCounter(event.getUser()).decrementAndGet();
        }
    }

    private AtomicInteger getCounter(Serializable user) {
        return users.computeIfAbsent(user, k -> new AtomicInteger());
    }

    public Set<Serializable> getActiveUsers() {
        return users.entrySet().stream()
                .filter(entry -> entry.getValue().intValue() > 0)
                .map(entry -> entry.getKey())

                .collect(Collectors.toSet());
    }

    public void submit() {
        System.out.println("websocket triggered");

        Calendar now = Calendar.getInstance();

        String time = now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);

        chat.send("Hallooooooooooo");
    }

    public void submitChatMsg() {
        System.out.println("websocket triggered for chatmsg");
        chat.send("Hallooooooooooo");

    }
}
