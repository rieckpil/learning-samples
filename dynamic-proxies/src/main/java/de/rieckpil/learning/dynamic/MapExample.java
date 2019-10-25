package de.rieckpil.learning.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class MapExample {

    public static void main(String[] args) {

        InvocationHandler handler = new LoggingInvocationHandler(Logger.getGlobal(), new ConcurrentHashMap<>());

        Map<String, Integer> map = (Map<String, Integer>)
                Proxy.newProxyInstance(Map.class.getClassLoader(), new Class<?>[]{Map.class}, handler);
        map.put("one", 1);
        map.put("two", 2);
        System.out.println(map);
    }

    public static <P> P simpleProxy(Class<P> clazz, P p) {
        return castedProxy(clazz, (proxy, method, args) -> method.invoke(p, args));
    }
    public static <P> P castedProxy(Class<P> clazz, InvocationHandler h) {
        return clazz.cast(Proxy.newProxyInstance(
                clazz.getClassLoader(), new Class<?>[] {clazz}, h
        ));
    }

}
