package de.rieckpil.learning.apachepoiword;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class XDocReportExample {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {
        XDocReportExample example = new XDocReportExample();
        // ByteArrayOutputStream byteArrayOutputStream = example.createDocumentForComparisonTest("/templates/Invoice" +
        //".docx");

        example.createPdfFromTemplateWithXDocReport("/templates/invoice.docx",
                "/Users/Philip/Desktop/junk/pdf/invoice_out.pdf");
    }


    public String createDocument() throws Exception {

        long start = System.currentTimeMillis();

        this.createPdfFromTemplateWithXDocReport("/templates/invoice.docx",
                "/Users/Philip/Desktop/junk/pdf/invoice_out.pdf");

        return " processing template took: " + (System.currentTimeMillis() - start);
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

    public void createPdfFromTemplateWithXDocReport(String inputFileName, String outputFileName) throws IOException,
            XDocReportException {

        InputStream in = this.getClass().getResourceAsStream(inputFileName);

        if (in == null) {
            log.error(String.format("No file found at path %s", inputFileName));
            return;
        }

        System.out.println("in.available() = " + in.available());

        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        IContext context = report.createContext();
        context.put("name1", "World1");
        context.put("name2", "World2");
        context.put("name3", "World3");
        context.put("name4", "World4");
        context.put("name5", "World5");
        context.put("name6", "World6");
        context.put("name7", "World7");
        context.put("name8", "World8");
        context.put("name9", "World9");
        context.put("name10", "World10");

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        report.process(context, out);

        XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(out.toByteArray()));
        PdfOptions options = PdfOptions.create();

        OutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
        PdfConverter.getInstance().convert(document, fileOutputStream, options);

        fileOutputStream.close();
        out.close();
        in.close();

        log.info(String.format("Successfully processed file %s to output path %s", inputFileName, outputFileName));
    }
}
