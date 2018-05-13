package de.rieckpil.learning.apachepoiword;

import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApachePoiWordApplication {


    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApachePoiWordApplication.class, args);

        XDocReportExample xDocReportExample = (XDocReportExample) applicationContext.getBean("XDocReportExample");

        for (int i = 0; i <= 500; i++) {
            xDocReportExample.createPdfFromTemplateWithXDocReportWithCache("/Users/Philip/Desktop/junk/pdf/invoice_out.pdf");
        }

        // framework is caching the reports -> XDocReportRegistry.getRegistry().getCachedReports().clear();

    }
}
