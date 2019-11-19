package de.rieckpil.learning.entity;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areacode;
    private String exchange;
    private String subscriber;

    public PhoneNumber(String areacode, String exchange, String subscriber) {
        this.areacode = areacode;
        this.exchange = exchange;
        this.subscriber = subscriber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" + "areacode=" + areacode + ", exchange="
                + exchange + ", subscriber=" + subscriber + "}";
    }
}
