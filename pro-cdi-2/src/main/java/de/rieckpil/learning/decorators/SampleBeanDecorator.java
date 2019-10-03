package de.rieckpil.learning.decorators;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

@Decorator
@Priority(APPLICATION)
public class SampleBeanDecorator implements SampleBeanInterface {

    @Inject
    @Any
    @Delegate
    private SampleBeanInterface sampleBean;

    @Override
    public String sayHello(String name) {
        return sampleBean.sayHello(name) + " - decorated";
    }

    @Override
    public String sayGoodbye(String name) {
        return sampleBean.sayGoodbye(name) + " - decorated";
    }
}
