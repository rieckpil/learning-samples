package de.rieckpil.learning.apachepoiword;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.containsString;

public class FileReadingTest {

    @Test
    public void givenFileName_whenUsingJarFile_thenFileData() throws Exception{
        String expectedData = "Apache License";

        Class clazz = Matchers.class;
        InputStream inputStream = clazz.getResourceAsStream("/LICENSE.txt");
        String data = readFromInputStream(inputStream);

        System.out.println("data = " + data);

        Assert.assertThat(data, containsString(expectedData));
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {

        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
