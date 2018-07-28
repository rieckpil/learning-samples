package de.rieckpil.learning.ping.control;

import javax.interceptor.Interceptors;

@Interceptors({PingInterceptor.class})
public class PingManufacturer {

    public String manufacturePing() {
        return "Hello";
    }
}
