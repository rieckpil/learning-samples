package de.rieckpil.learning;

import java.time.Duration;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	private final SimpMessagingTemplate template;
	private final TaskScheduler taskScheduler;

	public GreetingController(SimpMessagingTemplate template, TaskScheduler taskScheduler) {
		this.template = template;
		this.taskScheduler = taskScheduler;
	}

	@EventListener
	public void sendRandomMessage(BrokerAvailabilityEvent brokerAvailabilityEvent) {

		System.out.println("BrokerAvailable: " + brokerAvailabilityEvent.toString());

		if (brokerAvailabilityEvent.isBrokerAvailable()) {
			this.taskScheduler.scheduleAtFixedRate(
					() -> this.template.convertAndSend("/topic/greetings", new Greeting(UUID.randomUUID().toString())),
					Duration.ofMillis(1000));
		}

	}

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

}
