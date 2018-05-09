package de.rieckpil.learning.apachepoiword;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class ComparisonTest {

    public static final String INPUT_FILE_NAME = "/templates/Invoice.docx";

    public static void main(String[] args) throws Exception {

        XDocReportExample xDocReportExample = new XDocReportExample();
        Docx4jExample docx4jExample = new Docx4jExample();

        int amount = 2;

        long start = System.currentTimeMillis();

        for (int i = 0; i < amount; i++) {
            String outputPdfPath = "/Users/Philip/Desktop/junk/pdf/out_" + i + ".pdf";
            createPdf(outputPdfPath, xDocReportExample.createDocumentForComparisonTest(INPUT_FILE_NAME));
            //createPdf(outputPdfPath, docx4jExample.createDocumentForComparisonTest(INPUT_FILE_NAME));

        }

        System.out.println(String.format("took %s seconds for creating %s reports", (System.currentTimeMillis() -
                        start) / 1000,
                amount));

        System.exit(0);

    }

    public static void createPdf(String outputPdfPath, ByteArrayOutputStream outputStream) {

        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(outputStream.toByteArray());
            XWPFDocument document = new XWPFDocument(inStream);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(outputPdfPath));
            PdfConverter.getInstance().convert(document, out, options);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
