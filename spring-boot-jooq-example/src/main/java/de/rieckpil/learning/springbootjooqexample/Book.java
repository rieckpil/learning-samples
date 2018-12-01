package de.rieckpil.learning.springbootjooqexample;

import java.time.Instant;

import lombok.Data;

@Data
public class Book {
	private String title;
	private Instant published;
	private String isbn;
	private double price;
}
