package de.rieckpil.learning;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
public class GrapheneTest {

	// -Dwebdriver.chrome.driver=C:\Users\Philip\Downloads\chromedriver_win32\chromedriver.exe
	
	@Drone
	WebDriver browser;

	@Test
	public void validateAirhacksPage() {
		browser.get("http://airhacks.com");
		System.out.println(browser.getTitle());
	}

}
