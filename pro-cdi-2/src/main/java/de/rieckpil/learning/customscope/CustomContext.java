package de.rieckpil.learning.customscope;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;

public class CustomContext implements Context {

    private final ConcurrentHashMap<Contextual<?>, Object> instances = new ConcurrentHashMap<>();

    public CustomContext() {
        System.out.println("CustomContext.CustomContext");
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return CustomScoped.class;
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        @SuppressWarnings("unchecked")
        T instance = (T) instances.computeIfAbsent(contextual,
                c -> contextual.create(creationalContext));
        return instance;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        @SuppressWarnings("unchecked")
        T instance = (T) instances.get(contextual);
        System.out.println("CustomContext.get");
        return instance;
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
