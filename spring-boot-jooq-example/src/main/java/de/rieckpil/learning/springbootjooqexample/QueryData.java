package de.rieckpil.learning.springbootjooqexample;

import de.rieckpil.learning.springbootjooqexample.domain.tables.Posts;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

import static de.rieckpil.learning.springbootjooqexample.domain.tables.Posts.*;

@Component
@Order(2)
public class QueryData implements CommandLineRunner {

    @Autowired
    private DSLContext dslContext;


    @Override
    public void run(String... args) throws Exception {

        String title = (String) dslContext.select(POSTS.TITLE, POSTS.CONTENT).from(POSTS).where(POSTS.ID.eq(1L)).fetch()
                .getValue(0, "title");

        System.out.println("title = " + title);
    }
}
