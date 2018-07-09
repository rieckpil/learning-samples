package de.rieckpil.learning.jasperintro;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PerformanceTester {

    private String logoPath;
    private JasperReport jasperReport;

    @PostConstruct
    public void init() {

        this.logoPath = new ClassPathResource("jasper/spring-boot-logo.png").getPath();

        System.out.println("### this.logoPath: " + logoPath);

        ClassPathResource report = new ClassPathResource("jasper/performance.jrxml");

        try {
            InputStream inputStreamJasperReport = report.getInputStream();
            JasperDesign jasperDesign = JRXmlLoader.load(inputStreamJasperReport);
            this.jasperReport = JasperCompileManager.compileReport(jasperDesign);
        } catch (JRException | IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't initialize Jasper Report for Performance testing");
        }

    }

    public void createJasperReport(String outputPdfFileName, int amountOfBeans) {

        JRBeanCollectionDataSource beanColDataSource =
                new JRBeanCollectionDataSource(createPersons(amountOfBeans));

        Map parameters = new HashMap();
        parameters.put("ReportTitle", "Performance Test: " + outputPdfFileName);
        parameters.put("Author", "Prepared By Rieckpil");
        parameters.put("logo", this.logoPath);

        try {
            JasperPrint jprint = JasperFillManager.fillReport(
                    this.jasperReport, parameters, beanColDataSource);

            JasperExportManager.exportReportToPdfFile(jprint, outputPdfFileName);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    private List<Person> createPersons(int amountOfPersons) {

        return Stream.iterate(0, n -> n + 1)
                .map(e -> new Person("Philip", "Riecks", UUID.randomUUID().toString(), ThreadLocalRandom.current()
                        .nextLong()))
                .limit(amountOfPersons)
                .collect(Collectors.toList());
    }

    public class Person {

        private String firstName;
        private String lastName;
        private String id;
        private long number;

        public Person(String firstName, String lastName, String id, long number) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
            this.number = number;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            this.number = number;
        }
    }
}
