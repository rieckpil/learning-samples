package de.rieckpil.learning.reservationclient;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaIntegrationTest {

    private static String SPRING_INTEGRATION_KAFKA_TOPIC = "spring-integration-kafka.t";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @ClassRule
    public static KafkaEmbedded embeddedKafka =
            new KafkaEmbedded(1, true, SPRING_INTEGRATION_KAFKA_TOPIC);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
    }

    @Test
    public void testIntegration() throws Exception {

        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send(SPRING_INTEGRATION_KAFKA_TOPIC, "Hello Spring !!");
        }

    }

}
