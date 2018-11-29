package de.rieckpil.learning;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public Page<Person> persons(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "500") int size,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@RequestParam(name = "budget", required = false) Integer budget,
			@RequestParam(name = "dobLimit", required = false) Long dobLimit) {

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (firstname != null && !firstname.isEmpty()) {
			booleanBuilder.and(QPerson.person.firstname.eq(firstname));
		}

		if (lastname != null && !lastname.isEmpty()) {
			booleanBuilder.and(QPerson.person.lastname.eq(lastname));
		}

		if (budget != null && budget != 0) {
			booleanBuilder.and(QPerson.person.budget.goe(budget));
		}

		if (dobLimit != null && dobLimit != 0) {
			booleanBuilder.and(QPerson.person.dob.before(Instant.ofEpochSecond(dobLimit)));
		}

		return personRepository.findAll(booleanBuilder.getValue(), PageRequest.of(page, size));

	}

	// http://localhost:8080/persons/bySpecification?page=0&size=100&firstname=Max&budget=50000&dobLimit=946684800

	@GetMapping("/bySpecification")
	public List<Person> personsbySpecification(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "500") int size,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@RequestParam(name = "budget", required = false) Integer budget,
			@RequestParam(name = "dobLimit", required = false) Long dobLimit) {

		return personRepository.findAll(PersonSpecification.findByCriteria(lastname, firstname, budget, dobLimit),
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
