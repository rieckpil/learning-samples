package de.rieckpil.learning.apachepoiword;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class XDocReportExample {

    public static void main(String[] args) throws Exception {
        XDocReportExample example = new XDocReportExample();
        ByteArrayOutputStream byteArrayOutputStream = example.createDocumentForComparisonTest("/templates/Invoice.docx");
    }

    public ByteArrayOutputStream createDocumentForComparisonTest(String inputFileName) throws Exception {

        Resource resource = new ClassPathResource(inputFileName);

        if (resource == null) {
            System.out.println("Resource can't be null");
            return null;
        } else {

            InputStream in = resource.getInputStream();

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

            IContext context = report.createContext();
            context.put("name", "World");

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            report.process(context, out);

            return out;

            /**
             InputStream in2= new FileInputStream(new File("/Users/Philip/Desktop/Invoice_out.docx"));
             XWPFDocument document = new XWPFDocument(in2);

             // 2) Prepare Pdf options
             PdfOptions options2 = PdfOptions.create();

             // 3) Convert XWPFDocument to Pdf
             OutputStream out2 = new FileOutputStream(new File("HelloWord.pdf"));
             PdfConverter.getInstance().convert(document, out2, options2);
             **/
        }

    }
}
