package de.rieckpil.learning.ping.control;

import javax.ejb.Stateless;

@Stateless
public class PingManufacturer {

    public String manufacturePing() {
        return "Hello";
    }
}
