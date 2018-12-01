package de.rieckpil.learning.springbootjooqexample;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jooq.RecordValueReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootJooqExampleApplication {

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

}
