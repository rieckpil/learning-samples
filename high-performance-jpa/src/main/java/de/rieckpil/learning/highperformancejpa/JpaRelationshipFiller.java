package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Post;
import de.rieckpil.learning.highperformancejpa.entity.PostComment;
import de.rieckpil.learning.highperformancejpa.entity.PostDetails;
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
    public void run(String... args) {

        Post post = new Post();
        post.setTitle("King Kong");

        PostComment postComment = new PostComment();
        postComment.setReview("Nice movie! 4 ****");

        PostDetails postDetails = new PostDetails();

        post.setPostDetails(postDetails);
        post.addComment(postComment);

        entityManager.persist(post);

    }

    public void detachingExample() throws Exception{
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

    public void orphanRemovalAndCascadingTypeAll() {

        Post post = new Post();
        post.setTitle("King Kong");

        PostComment postComment2 = new PostComment();
        postComment2.setReview("Nice animal");

        PostComment postComment = new PostComment();
        postComment.setReview("Nice movie! 4 ****");

        post.addComment(postComment);
        post.addComment(postComment2);

        entityManager.persist(post);

        post.removeComment(postComment2);
    }
}
