package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="posts", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends AbstractModel{

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonBackReference(value = "posts.username")
    private User user;


    private String postContent;

    @Enumerated(EnumType.STRING)
    private PostTopic postTopic;

    public Post() {}

    //Getters
    public User getUser() {
        return user;
    }

    public String getPostContent() {
        return postContent;
    }

    public PostTopic getPostTopic() {
        return postTopic;
    }

    //Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostTopic(PostTopic postTopic) {
        this.postTopic = postTopic;
    }

    //Methods

    @Override
    public String toString() {
        return "Post{" +
                "user=" + user +
                ", postContent='" + postContent + '\'' +
                ", postTopic=" + postTopic +
                '}';
    }
}
