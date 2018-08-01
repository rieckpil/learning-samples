package de.rieckpil.learning.apachepoiword;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.rieckpil.learning.apachepoiword.entity.Invoice;
import de.rieckpil.learning.apachepoiword.entity.Property;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.SyntaxKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class XDocReportExample {

    /**
     * This is the best evaluated solution.
     */

    @Autowired
    private ObjectMapper objectMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private IXDocReport report;

    @PostConstruct
    public void init() {

        try (InputStream in = this.getClass().getResourceAsStream("/templates/MVP.docx")) {

            if (in == null) {
                log.error(String.format("No file found at path %s", "/templates/MVP.docx"));
                throw new IllegalArgumentException("can't find the requested cached MVP report");
            }

            report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        } catch (IOException | XDocReportException e) {
            throw new RuntimeException("unable to init cached MVP report", e.getCause());
        }

    }

    public static void main(String[] args) throws Exception {

        XDocReportExample example = new XDocReportExample();

        example.processMVPTemplate(1);

        // example.processFurtherVelocityTechnologies("/templates/MVP.docx");
    }

    public void processMVPTemplate(int counter) {

        String inputFileName = "/templates/MVP.docx";

        try (InputStream in = this.getClass().getResourceAsStream(inputFileName);
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             OutputStream fileOutputStream = new FileOutputStream(new File("/Users/Philip/Desktop/junk/mvp" + counter +
                     ".pdf"));
        ) {

            if (in == null) {
                log.error(String.format("No file found at path %s", inputFileName));
                return;
            }

            System.out.println("in.available() = " + in.available());

            FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
            fieldsMetadata.load("objekt", Property.class);

            Property property = new Property();
            property.setMarktwert(199454);

            System.out.println(objectMapper.writeValueAsString(property));

            IContext context = report.createContext();
            context.put("objekt", property);
            context.put("email", "mail@philipriecks.de");
            context.put("aktuellesDatum", LocalDate.now());

            report.process(context, out);

            XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(out.toByteArray()));
            PdfOptions options = PdfOptions.create();

            PdfConverter.getInstance().convert(document, fileOutputStream, options);

            log.info(String.format("Successfully processed file %s to output path %s", inputFileName, "/Users/Philip/Desktop/junk/advanced.pdf"));

        } catch (IOException | XDocReportException e) {
            e.printStackTrace();
        }

    }


    public String createDocument() throws Exception {

        long start = System.currentTimeMillis();

        this.createPdfFromTemplateWithXDocReport("/templates/invoice.docx",
                "/Users/Philip/Desktop/junk/pdf/invoice_out.pdf");

        return " processing template took: " + (System.currentTimeMillis() - start);
    }

    public String createDocumentWithCache() throws Exception {

        long start = System.currentTimeMillis();

        this.createPdfFromTemplateWithXDocReportWithCache("/Users/Philip/Desktop/junk/pdf/invoice_out.pdf");

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

    public void createPdfFromTemplateWithXDocReportWithCache(String outputFileName) throws
            IOException,
            XDocReportException {

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

        log.info(String.format("Successfully processed file to output path %s", outputFileName));
    }

    public void processFurtherVelocityTechnologies(String inputFileName) throws Exception {

        InputStream in = this.getClass().getResourceAsStream(inputFileName);

        if (in == null) {
            log.error(String.format("No file found at path %s", inputFileName));
            return;
        }

        System.out.println("in.available() = " + in.available());

        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        Invoice invoice = new Invoice("Musterrechnung", "42", null);
        Invoice invoice2 = new Invoice("Musterrechnung 2", "13237", Instant.now());

        List<String> names = Arrays.asList("Mike", "Franz", "Paul", "Tom");

        IContext context = report.createContext();
        context.put("invoice1", invoice);
        context.put("invoice2", invoice2);
        context.put("names", names);
        context.put("url", "https://rieckpil.de");
        context.put("comments", "<p><i>Text</i> coming from <b>Java context</b>.</p>");
        context.put("link", "<a href=\"$url\">Link</a> ");

        FieldsMetadata metadata = new FieldsMetadata();
        metadata.addFieldAsTextStyling("comments", SyntaxKind.Html);
        metadata.addFieldAsTextStyling("link", SyntaxKind.Html, true);
        metadata.addFieldAsList("invoice2.Date");
        report.setFieldsMetadata(metadata);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        report.process(context, out);

        XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(out.toByteArray()));
        PdfOptions options = PdfOptions.create();

        OutputStream fileOutputStream = new FileOutputStream(new File("/Users/Philip/Desktop/junk/advanced.pdf"));
        PdfConverter.getInstance().convert(document, fileOutputStream, options);

        fileOutputStream.close();
        out.close();
        in.close();

        log.info(String.format("Successfully processed file %s to output path %s", inputFileName, "/Users/Philip/Desktop/junk/advanced.pdf"));

    }
}
