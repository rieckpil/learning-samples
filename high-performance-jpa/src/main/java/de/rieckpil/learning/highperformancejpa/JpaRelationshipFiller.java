package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Post;
import de.rieckpil.learning.highperformancejpa.entity.PostComment;
import de.rieckpil.learning.highperformancejpa.entity.PostDetails;
import de.rieckpil.learning.highperformancejpa.entity.Tag;
import org.hibernate.jpa.QueryHints;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Profile("relationship")
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

        Tag tag = new Tag();
        tag.setName("Action");

        Tag tag2 = new Tag();
        tag2.setName("Thriller");

        post.getTags().add(tag);
        post.getTags().add(tag2);

        Post post2 = new Post();
        post2.setTitle("James Bond");
        post2.getTags().add(tag);

        entityManager.persist(post);
        entityManager.persist(post2);

        entityManager.flush();


        List<Post> posts = entityManager.createQuery("select p from Post p", Post.class).setHint
                (QueryHints.HINT_READONLY, true).getResultList();

        for (Post postFromDb : posts) {
            System.out.println("postFromDb.getTitle() = " + postFromDb.getTitle());
            postFromDb.setTitle(postFromDb.getTitle().toUpperCase());
        }

    }

    public void detachingExample() throws Exception {
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
