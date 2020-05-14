package de.rieckpl.learning;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class JacksonTest {

  @Test
  public void test() throws JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
    objectMapper.configure(MapperFeature.USE_ANNOTATIONS, true);

    System.out.println(objectMapper.writeValueAsString(new Person("Duke", Gender.MALE, true)));
    System.out.println(objectMapper.readValue("{\"firstName\":\"Duke\", \"gender\": \"8\", \"isRegistered\": true}", Person.class));


    ObjectNode objectNode = objectMapper.createObjectNode();
    objectNode.put("name", "duke");
    objectNode.put("value", new BigDecimal("42.50"));
    objectNode.set("address",
      objectMapper.createObjectNode()
        .put("street", "main")
        .put("postalCode", "91074"));
    objectNode.set("hobbies", objectMapper.createArrayNode().add("sports").add("bowling"));

    System.out.println(objectNode.toPrettyString());
    System.out.println(objectNode.toString());
    objectNode.fieldNames().forEachRemaining(s -> System.out.println(s));

  }

}

class Person {

  public String firstName;
  public Gender gender;
  public boolean isRegistered;

  public Person() {
  }

  public Person(String firstName, Gender gender, boolean isRegistered) {
    this.firstName = firstName;
    this.gender = gender;
    this.isRegistered = isRegistered;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public boolean isRegistered() {
    return isRegistered;
  }

  public void setRegistered(boolean registered) {
    isRegistered = registered;
  }

  @Override
  public String toString() {
    return "Person{" +
      "firstName='" + firstName + '\'' +
      ", gender=" + gender +
      ", isRegistered=" + isRegistered +
      '}';
  }
}

enum Gender {
  @JsonEnumDefaultValue MALE,
  FEMALE,
  DIVERSE
}
