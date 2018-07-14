package de.rieckpil.learning.user.control;

import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

@Named
@Dependent
public class UserReader extends AbstractItemReader {

    private BufferedReader br;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        br = new BufferedReader(
                new InputStreamReader(
                        Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream
                                        ("user.txt")));
    }

    @Override
    public Object readItem() throws Exception {
        String line = null;

        try {
            line = br.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return line;
    }
}
