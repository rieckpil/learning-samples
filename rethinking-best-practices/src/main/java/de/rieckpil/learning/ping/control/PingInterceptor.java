package de.rieckpil.learning.ping.control;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PingInterceptor {

    @AroundInvoke
    public Object tracePing(InvocationContext ic) throws Exception {

        System.out.println("tracing ping calls: " + ic.getMethod().getName() + "\n Amount of parameters:" + ic.getParameters().length);
        return ic.proceed();

    }
}
