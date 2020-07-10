package de.rieckpl.learning;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@Disabled("Issues with OkHttp dependency")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BasicWT {

  @LocalServerPort
  private int port;

  @Container
  public BrowserWebDriverContainer container = new BrowserWebDriverContainer()
    .withCapabilities(new ChromeOptions());

  @Test
  public void verifyPage() {
    this.container.getWebDriver().get("http://host.docker.internal:" + port + "/public/index");
    WebElement messageElement = this.container.getWebDriver().findElementById("message");
    assertEquals("Spring Boot Web Thymeleaf Example", messageElement.getText());
  }
}

