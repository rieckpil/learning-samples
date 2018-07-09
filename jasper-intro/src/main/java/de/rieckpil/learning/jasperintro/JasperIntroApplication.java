package de.rieckpil.learning.jasperintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class JasperIntroApplication {

    @Autowired
    private PerformanceTester performanceTester;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            String pdfOutputFileName = "/Users/Philip/Desktop/junk/jasper/performance/report";

            long start = System.currentTimeMillis();

            for (int i = 0; i < 10; i++) {
                performanceTester.createJasperReport(pdfOutputFileName + i + ".pdf",
                        100);
            }

            System.out.println("took: " + ((System.currentTimeMillis() - start) / 1000) + " seconds");

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(JasperIntroApplication.class, args);
    }
}
