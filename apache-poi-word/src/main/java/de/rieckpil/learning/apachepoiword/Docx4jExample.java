package de.rieckpil.learning.apachepoiword;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import java.io.File;

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

    }
}
