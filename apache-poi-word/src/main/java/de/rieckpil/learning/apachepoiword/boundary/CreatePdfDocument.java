package de.rieckpil.learning.apachepoiword.boundary;

import de.rieckpil.learning.apachepoiword.Docx4jExample;
import de.rieckpil.learning.apachepoiword.XDocReportExample;
import de.rieckpil.learning.apachepoiword.entity.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/documents")
public class CreatePdfDocument {

    private final Docx4jExample docx4jExample;

    private final XDocReportExample xDocReportExample;

    private AtomicLong counter = new AtomicLong();

    @PostMapping
    public String createDocument() throws Exception {

        Long currentCounter = counter.getAndIncrement();
        long start = System.currentTimeMillis();

        String result = xDocReportExample.createDocumentWithCache();

        return result + " whole processing took: " + (System.currentTimeMillis() - start);


    }
}
