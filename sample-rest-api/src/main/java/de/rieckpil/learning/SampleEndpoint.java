package de.rieckpil.learning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SampleEndpoint {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/todos")
    public ResponseEntity<String> getTodos() throws JsonProcessingException, InterruptedException {

        //  Thread.sleep(3_000);

        Faker faker = new Faker();
        List<Book> books = new ArrayList();

        for(int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setGenre(faker.book().genre());
            book.setTitle(faker.book().title());
            book.setIsbn(UUID.randomUUID().toString());
            books.add(book);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectMapper.writeValueAsString(books));
    }
}
