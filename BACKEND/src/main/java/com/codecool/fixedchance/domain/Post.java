package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="posts", schema="public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends AbstractModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull
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
