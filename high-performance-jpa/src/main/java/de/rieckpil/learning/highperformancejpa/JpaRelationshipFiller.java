package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Post;
import de.rieckpil.learning.highperformancejpa.entity.PostComment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class JpaRelationshipFiller implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        Post post = entityManager.find(Post.class, 1L);

        PostComment postComment = new PostComment();
        postComment.setPost(post);
        postComment.setReview("Nice movie! 4 ****");

        entityManager.persist(postComment);

        Thread.sleep(2 * 1000);

        entityManager.detach(postComment);

        postComment.setPost(null);
        postComment.setReview("Nice movie! 5 *****");

        entityManager.merge(postComment);

    }
}
