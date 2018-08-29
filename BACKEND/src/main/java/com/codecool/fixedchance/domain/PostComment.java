package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="post_comments", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostComment extends AbstractModel {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonBackReference
    private Post post;

    @Column(name = "user_id")
    private Integer userId;


    private String commentText;
    private Integer rating;

    public PostComment() {
    }

    // Getters

    public Integer getUserId() {
        return userId;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
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
                "userId=" + userId +
                ", post=" + post +
                ", commentText='" + commentText + '\'' +
                ", rating=" + rating +
                '}';
    }
}
