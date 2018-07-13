package de.rieckpil.learning.javaocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;

public class SimpleUploadOcr {

    public static void main(String[] args) throws Exception {

        Resource img = new ClassPathResource("IMG_3370.jpg");

        File file = new File(img.getURI());

        System.out.println(img.getFilename());

        ITesseract instance = new Tesseract();
        try {
            String result = instance.doOCR(file);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
