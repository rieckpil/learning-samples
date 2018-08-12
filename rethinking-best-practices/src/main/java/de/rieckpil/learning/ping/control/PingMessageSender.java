package de.rieckpil.learning.ping.control;

import de.rieckpil.learning.ping.entity.Ping;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.time.Instant;
import java.util.Date;

@Stateless
public class PingMessageSender {

    @Resource(mappedName = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;

    @Resource(mappedName = "jms/JmsQueue")
    private Queue jmsQueue;

    public void send() throws JMSException {

        Ping ping = new Ping();
        ping.setId(1L);
        ping.setContent("Hello Duke!");

        Jsonb jsonb = JsonbBuilder.create();
        String pingJson = jsonb.toJson(ping);

        MessageProducer producer;
        TextMessage message;
        try (Connection connection = jmsFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {

            JsonObject data = Json.createObjectBuilder().add("name", "Duke").add("age", 22).add("timestamp",
                    Instant.now().getEpochSecond()).build();

            String jsonString = data.toString();

            producer = session.createProducer(jmsQueue);
            message = session.createTextMessage();
            message.setText(jsonString + "-" + pingJson);
            System.out.println("Message sent to queue: " + jsonString);
            producer.send(message);
            producer.close();
        }
    }
}
