package com.airhacks.trainers.control;

import java.util.concurrent.atomic.AtomicInteger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class CircuitBreaker {

    AtomicInteger errorCounter = new AtomicInteger();

    @AroundInvoke
    public Object guard(InvocationContext ic) throws Exception {
        try {
            if(errorCounter.get() > 2){
                System.out.println("Circuit is open!");
                return null;
            }else {
                return ic.proceed();
            }
        } catch (Exception e) {
            System.out.println("Error happened! Error count: " + errorCounter.get());
            errorCounter.incrementAndGet();
            throw e;
        }
    }

}