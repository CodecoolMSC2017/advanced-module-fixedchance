package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    // Methods

    @Override
    public String toString() {
        return "PostComment{" +
                "userId=" + userId +
                ", post=" + post +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
