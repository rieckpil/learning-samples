package de.rieckpil.learning.springboot2book;

import de.rieckpil.learning.springboot2book.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@JsonTest
public class JacksonHttpStringSerializerIT {

    @Autowired
    private JacksonTester<Person> json;

    @Test
    public void testSerializingOfPersonObject() throws Exception {

        final Person person = new Person(1, "Phil", 22);

        assertThat(json.write(person)).isEqualTo("/person.json");
    }

}