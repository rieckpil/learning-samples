package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Announcement;
import de.rieckpil.learning.highperformancejpa.entity.Board;
import de.rieckpil.learning.highperformancejpa.entity.TopicPost;
import de.rieckpil.learning.highperformancejpa.entity.TopicStatistics;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.Instant;

@Component
@Profile("inheritance")
public class JpaInheritanceFiller implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(String... args) {

        Board board = new Board();
        board.setName("Board 1");

        entityManager.persist(board);

        TopicPost topicPost = new TopicPost();
        topicPost.setOwner("Phil");
        topicPost.setTitle("Inheritance");
        topicPost.setContent("Best practices");
        topicPost.setBoard(board);

        entityManager.persist(topicPost);

        Announcement announcement = new Announcement();
        announcement.setOwner("John Doe");
        announcement.setTitle("Effective Java");
        announcement.setValidUntil(Instant.now());
        announcement.setBoard(board);

        entityManager.persist(announcement);

        TopicStatistics postStatistics = new TopicStatistics();
        postStatistics.setTopic(topicPost);
        postStatistics.setViews(1L);

        entityManager.persist(postStatistics);

        TopicStatistics announcementStatistics = new TopicStatistics();
        announcementStatistics.setTopic(announcement);
        announcementStatistics.setViews(2L);

        entityManager.persist(announcementStatistics);

    }
}
