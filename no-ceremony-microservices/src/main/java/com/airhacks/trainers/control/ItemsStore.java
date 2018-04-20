package com.airhacks.trainers.control;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.interceptor.Interceptors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Interceptors(CircuitBreaker.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
public class ItemsStore {

    private Client client;
    private WebTarget itemsTarget;

    @Resource
    ManagedExecutorService mes;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newBuilder()
                        .connectTimeout(1, TimeUnit.SECONDS)
                        .readTimeout(1, TimeUnit.SECONDS)
                        .executorService(mes)
                        .build();
        this.itemsTarget = this.client.target("http://localhost:8080/items-microservice/resources/items");
    }

    public String getItems() {
        return this.itemsTarget.request().get(String.class);
    }

}