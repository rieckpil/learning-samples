package de.rieckpil.learning.apachepoiword;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class ComparisonTest {

    public static void main(String[] args) {

    }

    public static void createPdf(String outputPdfPath, ByteArrayOutputStream outputStream) {

        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream( outputStream.toByteArray() );
            XWPFDocument document = new XWPFDocument(inStream);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(outputPdfPath));
            PdfConverter.getInstance().convert(document, out, options);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
