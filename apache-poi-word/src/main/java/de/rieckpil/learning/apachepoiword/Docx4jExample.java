package de.rieckpil.learning.apachepoiword;

import org.apache.commons.lang3.Conversion;
import org.docx4j.Docx4J;
import org.docx4j.model.fields.FieldUpdater;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public class Docx4jExample {

    public static void main(String[] args) throws Exception {

        Docx4jExample example = new Docx4jExample();

        example.createDocument();
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
}
