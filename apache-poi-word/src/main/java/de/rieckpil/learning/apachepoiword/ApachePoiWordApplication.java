package de.rieckpil.learning.apachepoiword;

import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApachePoiWordApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApachePoiWordApplication.class, args);

        XDocReportExample example = new XDocReportExample();

        for (int i = 0; i <= 1; i++) {
            example.createPdfFromTemplateWithXDocReport("/templates/Invoice.docx",
                    "/Users/Philip/Desktop/junk/pdf/invoice_out.pdf");
        }

        // framework is caching the reports -> XDocReportRegistry.getRegistry().getCachedReports().clear();

    }
}
