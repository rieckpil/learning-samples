package de.rieckpil.learning.throttling.control;


import javax.interceptor.InvocationContext;

public class ThrottlingInterceptor {

    public Object throttleAPICall(InvocationContext invocationContext) throws Exception {

        System.out.println("Throttle API call ...");

        return invocationContext.proceed();
    }
}
