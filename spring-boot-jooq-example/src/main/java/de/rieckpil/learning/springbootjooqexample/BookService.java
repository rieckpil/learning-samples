package de.rieckpil.learning.springbootjooqexample;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rieckpil.learning.jooq.model.Tables;

@Service
public class BookService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	DSLContext dsl;

	public List<Book> getBooks() {
		
		Result<Record> fetch = dsl.select().from(Tables.BOOKS).fetch();
		
		for (Record record : fetch) {
			System.out.println(Instant.ofEpochMilli(record.get(Tables.BOOKS.PUBLISHED).getTime()));
		}
		
		return dsl.selectFrom(Tables.BOOKS).fetch().stream().map(e -> mapper.map(e, Book.class))
				.collect(Collectors.toList());
	}

}
