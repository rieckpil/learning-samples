package de.rieckpil.learning.jpaplayground;

import de.rieckpil.learning.jpaplayground.entities.Address;
import de.rieckpil.learning.jpaplayground.entities.Passport;
import de.rieckpil.learning.jpaplayground.entities.Person;
import de.rieckpil.learning.jpaplayground.repositories.AddressRepository;
import de.rieckpil.learning.jpaplayground.repositories.PassportRepository;
import de.rieckpil.learning.jpaplayground.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseFiller implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final PassportRepository passportRepository;
    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {


        Passport passport = createPassport("123");
        Person person = createPerson("Dk", "Kong", passport);

        System.out.println(passport.getPerson().getFirstName());

        personRepository.save(person);

        Thread.sleep(1000 * 5);

        Person toUpdatePerson = personRepository.findAll().get(0);
        toUpdatePerson.setFirstName("Falsch");
        personRepository.save(toUpdatePerson);



    }

    private Person createPerson(String firstName, String lastName, Passport passport) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPassport(passport, true);
        return person;
    }

    private Passport createPassport(String passportId) {
        Passport passport = new Passport();
        passport.setPassportId(passportId);
        return passport;
    }

    private Address createAddress(String city, String street, int houseNumber, Person person) {
        Address address = new Address();
        address.setCity(city);
        address.setHouseNumber(houseNumber);
        address.setStreet(street);
        address.setPerson(person);
        return address;
    }
}
