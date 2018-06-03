package de.rieckpil.learning.sendgridexample.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties("sendgrid")
@Validated
public class SendGridProperties {

    public static class Api {

        /**
         * Secret API Key
         */
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    /**
     * API properties for the Send Grid API
     */
    @NotNull
    private Api api;

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }
}


