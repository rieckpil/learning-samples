package de.rieckpil.learning.springboot2book;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.rieckpil.learning.springboot2book.entity.Person;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Year;

@JsonComponent
public class JacksonHttpStringSerializer extends JsonSerializer<Person> {

    @Override
    public void serialize(Person value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeObjectField("id", value.getId());
        gen.writeObjectField("lastName", value.getLastName().toUpperCase());
        gen.writeObjectField("dob", Year.now().getValue() - value.getAge());
        gen.writeEndObject();

    }
}
