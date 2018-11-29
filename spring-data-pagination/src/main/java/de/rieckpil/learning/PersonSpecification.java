package de.rieckpil.learning;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {

	public static Specification<Person> findByCriteria(final String lastname, final String firstname,
			final Integer budet, final Long dobLimit) {

		return new Specification<Person>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				if (lastname != null && !lastname.isEmpty()) {
					predicates.add(criteriaBuilder.equal(root.get("lastname"), lastname));
				}
				if (firstname != null && !firstname.isEmpty()) {
					predicates.add(criteriaBuilder.equal(root.get("firstname"), firstname));
				}
				if (budet != null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("budget"), budet));
				}
				if (dobLimit != null) {
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dob"), Instant.ofEpochSecond(dobLimit)));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

}
