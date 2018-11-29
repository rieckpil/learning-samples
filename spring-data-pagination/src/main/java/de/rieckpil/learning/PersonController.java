package de.rieckpil.learning;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public List<Person> persons(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "500") int size,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@RequestParam(name = "budget", required = false) Integer budget,
			@RequestParam(name = "dobLimit", required = false) Long dobLimit) {

		return personRepository.findAll(
				PersonSpecification.findByCriteria(lastname, firstname, budget, Instant.ofEpochSecond(dobLimit)),
				PageRequest.of(page, size, Sort.by("id"))).getContent();
	}

	@GetMapping("/byExample")
	public List<Person> personsByExample(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "500") int size) {

		Person p = new Person();
		p.setFirstname("Max");

		Example<Person> example = Example.of(p);

		return personRepository.findAll(example, PageRequest.of(page, size, Sort.by("id"))).getContent();
	}
}
