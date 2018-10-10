package de.rieckpil.learning;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunAsClient
@RunWith(Arquillian.class)
public class IndexIT {
	
	@Drone
	WebDriver browser;
	
	@Test
	public void testCopy(@InitialPage IndexPage page) {
		String result = page.copy("Duke");
		assertEquals("Duke", result);
	}

}
