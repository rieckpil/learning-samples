package de.rieckpil.learning.springbootjooqexample;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static de.rieckpil.learning.springbootjooqexample.domain.tables.Posts.POSTS;

@Component
public class InsertData implements CommandLineRunner {

    @Autowired
    private DSLContext dslContext;

    @Override
    public void run(String... args) throws Exception {

        // @formatter:off
        dslContext
                .insertInto(POSTS)
                .columns(POSTS.TITLE, POSTS.CONTENT, POSTS.CREATED_ON)
                .values("Hello World", "Lorem ipsum dolor sit amet", Instant.now().getEpochSecond())
                .execute();
        // @formatter:on

    }
}
