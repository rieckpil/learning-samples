package de.rieckpil.learning.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DoNothingInterceptor {

    @AroundInvoke
    public Object doFoo(InvocationContext invocationContext) throws Exception {
        return invocationContext.proceed();
    }
}
