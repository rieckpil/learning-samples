package de.rieckpil.learning.jasperintro;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JasperReportController {

    @GetMapping("/jasper")
    public void getJasperReport() {

        String jrxmlFileName = "jasper/first_report.jrxml";
        String pdfFileName = "jasper/first_report.pdf";

        try {

            InputStream inputStreamJasperReport = this.getClass().getClassLoader().getResourceAsStream(jrxmlFileName);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStreamJasperReport);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String, Object> arguments = new HashMap<>();
            arguments.put("NAME", "Hello World!");

            JasperPrint jprint = JasperFillManager.fillReport(jasperReport, arguments);

            JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);

            byte[] outputByteArray = JasperExportManager.exportReportToPdf(jprint);

            System.out.println("Successfully generated report");

        } catch (JRException e) {
            e.printStackTrace();
        }


    }
}
