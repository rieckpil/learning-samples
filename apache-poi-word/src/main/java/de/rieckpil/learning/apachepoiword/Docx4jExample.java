package de.rieckpil.learning.apachepoiword;

import de.rieckpil.learning.apachepoiword.entity.Invoice;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.Docx4J;
import org.docx4j.model.fields.FieldUpdater;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;

@Service
public class Docx4jExample {

    public static void main(String[] args) throws Exception {

        Docx4jExample example = new Docx4jExample();

        //example.createDocument();
        // example.replaceVariablesInWord("Boo", "22");

        example.createInvoicePdf(new Invoice("Hans", "31"), 1);
        example.convertToPdf("/Users/Philip/Desktop/junk/pdf/invoice_out_1.docx",
                "/Users/Philip/Desktop/junk/pdf/invoice_out_1.pdf" );
    }

    public void createDocument() throws Exception {
        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();

        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
        mainDocumentPart.addParagraphOfText("Welcome To Baeldung");

        File exportFile = new File("welcome.docx");
        wordPackage.save(exportFile);

        FieldUpdater updater = new FieldUpdater(wordPackage);
        updater.update(true);
        OutputStream os = new java.io.FileOutputStream("welcome.pdf");
        Docx4J.toPDF(wordPackage, os);

        os.close();

    }

    public OutputStream createDocumentForComparisonTest(String inputFileName, String outputFileName) throws Exception{

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new File(inputFileName));
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("name", "Phil");
        mappings.put("age", "1137");

        documentPart.variableReplace(mappings);

        OutputStream result = new FileOutputStream(outputFileName);

        wordMLPackage.save(result);

        return result;
    }

    public void replaceVariablesInWord(String name, String age) throws Exception {

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new File(System.getProperty("user.dir") +"/invoice.docx"));
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();


        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("name", name);
        mappings.put("age", age);

        documentPart.variableReplace(mappings);

        wordMLPackage.save(new File("invoice_out.docx"));
    }

    public String createInvoicePdf(Invoice invoice, long counter) throws Exception{

        String resultString = "Replacing variable tooks: ";

        long start = System.currentTimeMillis();

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new File(System.getProperty("user.dir") +"/invoice.docx"));
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("name", invoice.getName());
        mappings.put("age", invoice.getAge());

        documentPart.variableReplace(mappings);

        wordMLPackage.save(new File("/Users/Philip/Desktop/junk/pdf/invoice_out_" + counter+ ".docx"));

        resultString += (System.currentTimeMillis() - start) + " and creating PDF took: ";
        start = System.currentTimeMillis();

        //FieldUpdater updater = new FieldUpdater(wordMLPackage);
        //updater.update(true);
        //OutputStream os = new java.io.FileOutputStream("/Users/Philip/Desktop/junk/pdf/welcome_" + counter+ ".pdf");
        //Docx4J.toPDF(wordMLPackage, os);

        //os.close();

        resultString += (System.currentTimeMillis() - start) + " ";

        return resultString;
    }

    public void convertToPdf(String docPath, String pdfPath) {
        try {
            InputStream doc = new FileInputStream(new File(docPath));
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfConverter.getInstance().convert(document, out, options);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
