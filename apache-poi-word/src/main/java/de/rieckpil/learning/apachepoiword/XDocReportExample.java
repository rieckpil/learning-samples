package de.rieckpil.learning.apachepoiword;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.ITemplateEngine;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class XDocReportExample {

    public static void main(String[] args) throws Exception {
        XDocReportExample example = new XDocReportExample();
        example.processTemplate();
    }

    public void processTemplate() throws Exception {

        Resource resource = new ClassPathResource("/templates/Invoice.docx");

        if (resource == null) {
            System.out.println("Resource can't be null");
        } else {

            InputStream in = resource.getInputStream();

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            // 2) Create Java model context
            IContext context = report.createContext();
            context.put("name", "World");

            // 3) Generate report by merging Java model with the ODT
            OutputStream out = new FileOutputStream(new File("/Users/Philip/Desktop/Invoice_out.docx"));

            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
            report.process(context, out);

            /**
            InputStream in2= new FileInputStream(new File("/Users/Philip/Desktop/Invoice_out.docx"));
            XWPFDocument document = new XWPFDocument(in2);

            // 2) Prepare Pdf options
            PdfOptions options2 = PdfOptions.create();

            // 3) Convert XWPFDocument to Pdf
            OutputStream out2 = new FileOutputStream(new File("HelloWord.pdf"));
            PdfConverter.getInstance().convert(document, out2, options2);
            **/
            System.exit(0);
        }

    }
}
