package de.rieckpil.learning;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class ITextExample {

    public static void main(String[] args) throws IOException {
        createSimplePdf();
        createPdfFromHtml();
    }

    private static void createSimplePdf() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter("itext7-community.pdf"));
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World!"));
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        Div div = new Div().setFont(font).setFontSize(14);
        div.add(new Paragraph("iText is:"));

        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022");

        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you cry"));

        div.add(list);
        document.add(div);
        document.close();
    }

    private static void createPdfFromHtml() throws IOException {
        HtmlConverter.convertToPdf(new ClassPathResource("/templates/invoice.html").getFile(), new File("itext7-community" +
                "-html2pdf" +
                ".pdf"));
    }
}
