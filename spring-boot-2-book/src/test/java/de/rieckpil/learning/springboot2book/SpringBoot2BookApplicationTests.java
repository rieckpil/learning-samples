package de.rieckpil.learning.springboot2book;

import de.rieckpil.learning.springboot2book.christmas.ChristmasBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot2BookApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testInstantiationOfFooBean() {
        assertThat(context.getBeansOfType(Foo.class).size(), is(equalTo(1)));
        // the bean gets the name of the method it was created when the Java based bean configuration is used
        assertThat(context.containsBean("foo2"), is(true));
    }

    @Test
    public void testChristmasBeanIsNotPresent() {
        assertThat(context.getBeansOfType(ChristmasBean.class).size(), is(equalTo(0)));
    }

}
