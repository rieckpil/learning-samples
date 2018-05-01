package de.rieckpil.learning.reservationclient;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaIntegrationTest {

    private static String SPRING_INTEGRATION_KAFKA_TOPIC = "KAFKA_INTEGRATION";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static Consumer<String, String> consumer;

    @ClassRule
    public static KafkaEmbedded embeddedKafka =
            new KafkaEmbedded(1, true, SPRING_INTEGRATION_KAFKA_TOPIC);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "false", embeddedKafka);
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<String, String>(
                consumerProps, new StringDeserializer(), new StringDeserializer());
        consumer = cf.createConsumer();
        embeddedKafka.consumeFromAllEmbeddedTopics(consumer);
    }

    @Test
    public void testIntegration() throws Exception {

        kafkaTemplate.send(SPRING_INTEGRATION_KAFKA_TOPIC, "1", "bar");
        ConsumerRecord<String, String> received = KafkaTestUtils.getSingleRecord(consumer,
                SPRING_INTEGRATION_KAFKA_TOPIC);
        System.out.println("received.value() = " + received.value());

    }

}
