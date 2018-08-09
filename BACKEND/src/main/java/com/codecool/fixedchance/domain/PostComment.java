package com.codecool.fixedchance.domain;

import javax.persistence.*;

@Entity
@Table(name="post_comments", schema="public")
public class PostComment extends AbstractModel {


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private SimpleUser simpleUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    private String commentText;
    private Integer rating;

    public PostComment() {
    }

    // Getters
    public SimpleUser getSimpleUser() {
        return simpleUser;
    }

    public Post getPost() {
        return post;
    }

    public String getCommentText() {
        return commentText;
    }

    public Integer getRating() {
        return rating;
    }

    // Setters
    public void setSimpleUser(SimpleUser simpleUser) {
        this.simpleUser = simpleUser;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    // Methods
    @Override
    public String toString() {
        return "PostComment{" +
                "simpleUser=" + simpleUser +
                ", post=" + post +
                ", commentText='" + commentText + '\'' +
                ", rating=" + rating +
                '}';
    }
}
