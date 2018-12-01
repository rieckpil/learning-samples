package de.rieckpil.learning.springbootjooqexample;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jooq.RecordValueReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootJooqExampleApplication implements CommandLineRunner {

	@Autowired
	private BookService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJooqExampleApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
				.addValueReader(new RecordValueReader());
		return mapper;
	}

	@Override
	public void run(String... args) throws Exception {

		for (Book book : service.getBooks()) {
			System.out.println(book.toString());
		}

	}

}
