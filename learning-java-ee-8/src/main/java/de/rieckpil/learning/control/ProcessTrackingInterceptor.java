package de.rieckpil.learning.control;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Tracked
@Priority(Interceptor.Priority.APPLICATION)
public class ProcessTrackingInterceptor {

    @AroundInvoke
    public Object arroundInvoke(InvocationContext context) throws Exception {
        System.out.println("-- tracking");
        System.out.println(context.getMethod().getName());
        return context.proceed();
    }
}
