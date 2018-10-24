package de.rieckpil.learning;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

@Service
public class TemplateProcessor {

	@Autowired
	TemplateEngine templateEngine;

	public void processTemplate() throws IOException, DocumentException {

		final Context ctx = new Context(Locale.GERMAN);
		ctx.setVariable("name", "Phil");
		ctx.setVariable("subscriptionDate", new Date());
		ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		ctx.setVariable("imageResourceName", "https://rieckpil.de/wp-content/uploads/2018/10/java_ee_logo_short.jpg");

		final String htmlContent = this.templateEngine.process("index.html", ctx);

		String fileName = UUID.randomUUID().toString();
		File outputFile = new File(fileName + ".pdf");

		try (FileOutputStream os = new FileOutputStream(outputFile)) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();
			System.out.println("PDF created successfully");
		}
	}

}
