package de.rieckpil.learning.ping.control;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven
public class PingMessagingBean implements MessageListener {

    @Override
    public void onMessage(Message message) {

        try {
            System.out.println(message.toString());
            System.out.println(message.getJMSTimestamp());
            System.out.println(message.getJMSType());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
