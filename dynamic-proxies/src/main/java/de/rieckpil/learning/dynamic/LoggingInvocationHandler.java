package de.rieckpil.learning.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

public class LoggingInvocationHandler implements InvocationHandler {

    private final Logger log;
    private final Object o;

    public LoggingInvocationHandler(Logger log, Object o) {
        this.log = log;
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info(() -> "Entering " + method + " with parameters " + Arrays.toString(args));
        long start = System.nanoTime();
        try {
            return method.invoke(o, args);
        } finally {
            long nanos = System.nanoTime() - start;
            log.info(() -> "Exiting " + method + " with parameters " + Arrays.toString(args) + " took " + nanos + "ns");
        }
    }
}
