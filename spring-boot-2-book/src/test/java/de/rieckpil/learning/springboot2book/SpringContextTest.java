package de.rieckpil.learning.springboot2book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestBeansConfiguration.class)
public class SpringContextTest {

    @Autowired
    private Foo foo;

    @Test
    public void testFooInstanceExistence() {
        assertNotNull(foo);
    }

}
