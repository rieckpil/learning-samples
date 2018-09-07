package de.rieckpil.learning.apachepoiword;

import com.lowagie.text.pdf.PdfWriter;
import de.rieckpil.learning.apachepoiword.entity.Invoice;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.hslf.record.Document;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.model.fields.FieldUpdater;
import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class Docx4jExample {

    public static void main(String[] args) throws Exception {

        Docx4jExample example = new Docx4jExample();

        example.createExampleForMVP();

    }

    public String createInvoicePdf(Invoice invoice, long counter) throws Exception {

        String resultString = "Replacing variable tooks: ";

        long start = System.currentTimeMillis();

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new ClassPathResource("templates/Invoice.docx").getInputStream());

        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        List<Map<DataFieldName, String>> data = new ArrayList<Map<DataFieldName, String>>();

        VariablePrepare.prepare(wordMLPackage);

        Map<DataFieldName, String> map1 = new HashMap<>();
        map1.put(new DataFieldName("FirstName"), UUID.randomUUID().toString());
        map1.put(new DataFieldName("LastName"), "myFirstname2");
        data.add(map1);


        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("name1", UUID.randomUUID().toString());
        mappings.put("name2", UUID.randomUUID().toString());
        mappings.put("name3", UUID.randomUUID().toString());
        mappings.put("name4", UUID.randomUUID().toString());

        // MailMerger.performMerge(wordMLPackage, map1, true);

        documentPart.variableReplace(mappings);

        wordMLPackage.save(new File("/Users/Philip/Desktop/junk/pdf/invoice_out_" + counter + ".docx"));

        resultString += (System.currentTimeMillis() - start) + " and creating PDF took: ";
        start = System.currentTimeMillis();

        resultString += (System.currentTimeMillis() - start) + " ";

        return resultString;
    }

    public void createExampleForMVP() throws Exception {

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new ClassPathResource("templates/MVP.docx").getInputStream());

        VariablePrepare.prepare(wordMLPackage);

        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        HashMap<String, String> variables = new HashMap<>();
        variables.put("DatumHeute", LocalDate.now().toString());
        variables.put("Strasse", "Braumeisterweg");
        variables.put("Hausnummer", "42");
        variables.put("Stadt", "Hopfengold");
        variables.put("Marktwert", "192.20 €");

        documentPart.variableReplace(variables);

        wordMLPackage.save(new File("/Users/Philip/Desktop/junk/pdf/mvp_out.docx"));

        FieldUpdater updater = new FieldUpdater(wordMLPackage);
        updater.update(true);

        Docx4J.toPDF(wordMLPackage, new FileOutputStream("/Users/Philip/Desktop/junk/pdf/mvp.pdf"));

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

    public ByteArrayOutputStream createDocumentForComparisonTest(String inputFileName) throws Exception {

        Resource resource = new ClassPathResource(inputFileName);

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(resource.getFile());
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("name", "Phil");
        documentPart.variableReplace(mappings);

        ByteArrayOutputStream result = new ByteArrayOutputStream();

        wordMLPackage.save(result);

        return result;
    }

    public void replaceVariablesInWord(String name, String age) throws Exception {

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(new File(System.getProperty("user.dir") + "/invoice.docx"));
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();


        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("name", name);
        mappings.put("age", age);

        documentPart.variableReplace(mappings);

        wordMLPackage.save(new File("invoice_out.docx"));
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
