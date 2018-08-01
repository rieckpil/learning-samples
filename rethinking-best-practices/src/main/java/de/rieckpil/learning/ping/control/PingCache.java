package de.rieckpil.learning.ping.control;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PingCache {

    private ConcurrentHashMap<String, Object> cache;

    @PostConstruct
    public void init() {
        this.cache = new ConcurrentHashMap<>();
    }

    @Produces
    @Cache
    public ConcurrentHashMap<String, Object> expose() {
        return this.cache;
    }
}
