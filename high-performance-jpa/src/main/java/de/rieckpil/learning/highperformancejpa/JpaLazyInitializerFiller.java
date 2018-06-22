package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Attachment;
import de.rieckpil.learning.highperformancejpa.entity.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;

@Component
@Transactional
@Slf4j
public class JpaLazyInitializerFiller implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {


        Attachment attachment = new Attachment();

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("data.sql").getFile());

        log.info("Resource {} loaded", file.getName());

        byte[] fileBytes = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(fileBytes);
        fis.close();

        System.out.println("fileBytes.length = " + fileBytes.length);

        attachment.setName(file.getName());
        attachment.setMediaType(MediaType.TEXT);
        attachment.setContent(fileBytes);

        entityManager.persist(attachment);

        entityManager.detach(attachment);

        entityManager.flush();

        Attachment attachmentFromDb = entityManager.find(Attachment.class, 1L);

        log.info("--- Attachment {} fetched", attachmentFromDb.getName());

        log.info("--- Attachment has {} bytes", attachmentFromDb.getContent().length);

    }
}
