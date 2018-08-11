package de.rieckpil.learning.ping.control;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "jms/JmsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class PingMessagingBean implements MessageListener {

    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;

        try {
            JsonReader jsonReader = Json.createReader(new StringReader(textMessage.getText()));
            JsonObject jobj = jsonReader.readObject();
            System.out.print("Got new message on queue: " + jobj);
            System.out.println("\n");
        } catch (JMSException e) {
            System.err.println(e.getMessage());
        }

    }
}
