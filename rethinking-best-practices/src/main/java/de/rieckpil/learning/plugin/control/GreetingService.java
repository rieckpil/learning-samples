package de.rieckpil.learning.plugin.control;

import de.rieckpil.api.greeter.Greeter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Startup
@Singleton
public class GreetingService {

    @Inject
    @Any
    Instance<Greeter> greeters;

    @PostConstruct
    public void greetEveryone() {

        String personName = "Philip";
        System.out.println("Let's greet: " + personName);

        for (Greeter greeter : greeters) {
            greeter.greetPerson(personName);
        }
    }
}
