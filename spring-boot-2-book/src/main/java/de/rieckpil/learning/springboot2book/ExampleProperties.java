package de.rieckpil.learning.springboot2book;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties("example")
@Validated
public class ExampleProperties {

    public static class Server {
        private String name;
        private URL url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public URL getUrl() {
            return url;
        }

        public void setUrl(URL url) {
            this.url = url;
        }
    }

    public static enum Environment {
        dev, prod, cloud
    }

    @NotNull
    @Size(min = 1)
    private String theGreeting;

    @Min(10)
    private Integer interval;

    private Map<Environment, Map<String, Object>> environments;

    private List<Server> servers;

    public String getTheGreeting() {
        return theGreeting;
    }

    public void setTheGreeting(String theGreeting) {
        this.theGreeting = theGreeting;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Map<Environment, Map<String, Object>> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<Environment, Map<String, Object>> environments) {
        this.environments = environments;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return "ExampleProperties{" +
                "theGreeting='" + theGreeting + '\'' +
                ", interval=" + interval +
                ", environments=" + environments +
                ", servers=" + servers +
                '}';
    }
}
