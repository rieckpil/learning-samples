package de.rieckpil.learning.springboot2bookreactive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document(collection = "films")
public class Film {

    @Id
    private String id;

    private final String title;

    private final Integer releaseYear;

    @JsonIgnore
    private final List<Actor> actors = new ArrayList<>();

    @JsonCreator
    public Film(
            @JsonProperty("title") String title,
            @JsonProperty("releaseYear") Integer releaseYear
    ) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public List<Actor> getActors() {
        return Collections.unmodifiableList(actors);
    }
}