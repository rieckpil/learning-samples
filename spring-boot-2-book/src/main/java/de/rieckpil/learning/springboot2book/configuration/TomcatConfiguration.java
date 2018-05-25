package de.rieckpil.learning.springboot2book.configuration;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public class TomcatConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
       factory.addConnectorCustomizers(connector -> {
           connector.setProxyName("mycorporateproxy");
           connector.setProxyPort(8080);
       });
    }
}
