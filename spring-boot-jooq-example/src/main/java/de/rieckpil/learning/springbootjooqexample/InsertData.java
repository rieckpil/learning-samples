package de.rieckpil.learning.springbootjooqexample;

import org.jooq.BatchBindStep;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static de.rieckpil.learning.springbootjooqexample.domain.tables.Posts.POSTS;

@Component
@Order(1)
public class InsertData implements CommandLineRunner {

    @Autowired
    private DSLContext dslContext;

    @Override
    public void run(String... args) throws Exception {

        // @formatter:off

        dslContext.deleteFrom(POSTS).execute();

        dslContext
                .insertInto(POSTS)
                .columns(POSTS.ID, POSTS.TITLE, POSTS.CONTENT, POSTS.CREATED_ON)
                .values(1L, "Hello World", "Lorem ipsum dolor sit amet", Instant.now().getEpochSecond())
                .execute();

        dslContext
                .insertInto(POSTS)
                .columns(POSTS.ID, POSTS.TITLE, POSTS.CONTENT, POSTS.CREATED_ON)
                .values(2L, "Hello World2", "Lorem ipsum dolor sit amet2", Instant.now().getEpochSecond())
                .execute();

        dslContext
                .insertInto(POSTS)
                .columns(POSTS.ID, POSTS.TITLE, POSTS.CONTENT, POSTS.CREATED_ON)
                .values(1L, "Upsert", "Upserts are cool!", Instant.now().getEpochSecond())
                .onDuplicateKeyUpdate()
                .set(POSTS.TITLE, "Upsert")
                .set(POSTS.CONTENT, "Upserts are cool!")
                .execute();

        BatchBindStep batch = dslContext.batch(dslContext.insertInto(POSTS, POSTS.TITLE, POSTS.CONTENT).values
                ("?", "?"));

        for (int i = 0; i < 10; i++) {
            batch.bind(String.valueOf(i), String.format("Post no. %d", i));
        }

        int[] insertCounts = batch.execute();

        System.out.println("insertCounts = " + insertCounts.length);

        // @formatter:on

    }
}
