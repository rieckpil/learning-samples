package de.rieckpil.learning.apachepoiword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WordTemplate {

    public static final String SOURCE_FILE = "/Users/Philip/Desktop/lipsum.doc";
    public static final String OUTPUT_FILE = "/Users/Philip/Desktop/new-lipsum.doc";

    public static void main(String[] args) throws Exception {
        WordTemplate instance = new WordTemplate();
        HWPFDocument doc = instance.openDocument(SOURCE_FILE);
        if (doc != null) {
            doc = instance.replaceText(doc, "$MYVALUE", "LOLLOLO");
            instance.saveDocument(doc, OUTPUT_FILE);
        }
    }

    private HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
        Range r = doc.getRange();
        for (int i = 0; i < r.numSections(); ++i) {
            Section s = r.getSection(i);
            for (int j = 0; j < s.numParagraphs(); j++) {
                Paragraph p = s.getParagraph(j);
                for (int k = 0; k < p.numCharacterRuns(); k++) {
                    CharacterRun run = p.getCharacterRun(k);
                    String text = run.text();
                    if (text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return doc;
    }

    private HWPFDocument openDocument(String file) throws Exception {
        return new HWPFDocument(new POIFSFileSystem(
                new File(file)));
    }

    private void saveDocument(HWPFDocument doc, String file) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            doc.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}