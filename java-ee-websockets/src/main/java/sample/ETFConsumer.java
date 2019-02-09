package sample;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@Startup
@Singleton
public class ETFConsumer {

	private Session session;

	@PostConstruct
	public void init() {

		WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

		try {
			this.session = webSocketContainer.connectToServer(ETFClient.class,
					URI.create("ws://localhost:8080/java-ee-websockets/dukeetf"));
		} catch (DeploymentException | IOException ex) {
			Logger.getLogger(ETFConsumer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void doFoo() {
		System.out.println(session.getId());
	}
}
