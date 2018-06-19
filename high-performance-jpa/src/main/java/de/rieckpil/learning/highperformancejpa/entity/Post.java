package de.rieckpil.learning.highperformancejpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostDetails postDetails;

    public void setPostDetails(PostDetails postDetails) {
        this.postDetails = postDetails;
        postDetails.setPost(this);
    }

    public void addComment(PostComment postComment) {
        this.comments.add(postComment);
        postComment.setPost(this);
    }

    public void removeComment(PostComment postComment) {
        this.comments.remove(postComment);
        postComment.setPost(null);
    }
}
