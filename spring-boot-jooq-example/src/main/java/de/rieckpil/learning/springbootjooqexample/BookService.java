package de.rieckpil.learning.springbootjooqexample;

import java.util.List;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Table;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	DSLContext dsl;

	public List<Book> getBooks() {
		return null;
	}

}
