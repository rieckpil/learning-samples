package de.rieckpil.learning;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloWorldTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(HelloWorld.class);
    }

    @Test()
    public void sayHelloTest() {
        HelloWorld helloWorld = new HelloWorld();
        Assert.assertEquals(helloWorld.sayHello(), "Hello World");
    }

}