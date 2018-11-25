package de.rieckpil.learning;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDroolsSampleApplication implements CommandLineRunner {

    @Autowired
    KieContainer kieContainer;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDroolsSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(true);
        taxiRide.setDistanceInMile(9L);
        Fare rideFare = new Fare();

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("rideFare", rideFare);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(rideFare.getTotalFare());

    }
}
