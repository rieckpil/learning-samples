package de.rieckpil.learning.jasperintro;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JasperReportController {

    @GetMapping("/jasper")

    public void getJasperReport() {
        String sourceFileName = "jasper/second_report.jrxml";
        String pdfFileName = "second_report.pdf";

        ArrayList<DataBean> dataBeans = new ArrayList<>();
        dataBeans.add(new DataBean("Phil", "Germany"));
        dataBeans.add(new DataBean("Tom", "USA"));

        JRBeanCollectionDataSource beanColDataSource =
                new JRBeanCollectionDataSource(dataBeans);

        Map parameters = new HashMap();
        parameters.put("ReportTitle", "List of Contacts");
        parameters.put("Author", "Prepared By Rieckpil");

        try {
            InputStream inputStreamJasperReport = this.getClass().getClassLoader().getResourceAsStream(sourceFileName);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStreamJasperReport);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jprint = JasperFillManager.fillReport(
                    jasperReport, parameters, beanColDataSource);

            JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private void createFirstReport() {
        String jrxmlFileName = "jasper/first_report.jrxml";
        String pdfFileName = "first_report.pdf";

        try {

            InputStream inputStreamJasperReport = this.getClass().getClassLoader().getResourceAsStream(jrxmlFileName);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStreamJasperReport);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String, Object> arguments = new HashMap<>();
            arguments.put("ReportTitle", "Hello World!");

            JasperPrint jprint = JasperFillManager.fillReport(jasperReport, arguments);

            JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);

            byte[] outputByteArray = JasperExportManager.exportReportToPdf(jprint);

            System.out.println("Successfully generated report");

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
