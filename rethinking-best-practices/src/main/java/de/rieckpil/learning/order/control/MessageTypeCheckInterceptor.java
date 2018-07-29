package de.rieckpil.learning.order.control;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

public class MessageTypeCheckInterceptor {

    @AroundInvoke
    private Object audit(InvocationContext ic) throws Exception {

        Method method = ic.getMethod();

        if ("onMessage".equals(method.getName())) {
            ExpectedMessageType messageType = method.getAnnotation(ExpectedMessageType.class);
            Class type = messageType.value();

            Object messageParameter = ic.getParameters()[0];

            if (!type.isAssignableFrom(messageParameter.getClass())) {
                System.out.println("throw exception!!!");
            }

        }

        return ic.proceed();

    }
}
