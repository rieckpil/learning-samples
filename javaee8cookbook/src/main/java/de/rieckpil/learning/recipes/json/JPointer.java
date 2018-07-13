package de.rieckpil.learning.recipes.json;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;

public class JPointer {

    public static void main(String[] args) throws IOException {

        try (InputStream is = JPointer.class.getClassLoader().getResourceAsStream("user.json");
             JsonReader jr = Json.createReader(is)) {

            JsonStructure js = jr.read();
            JsonPointer jp = Json.createPointer("/user/profile");
            JsonValue jv = jp.getValue(js);
            System.out.println("profile: " + jv);
        }
    }
}
