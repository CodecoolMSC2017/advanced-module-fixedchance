package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.*;
import com.google.api.client.util.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="posts", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends AbstractModel{


    @NotNull
    private String userName;

    private String postContent;

    @NotNull
    private String postTopic;

    public Post() {}

    //Getters
    public String getUserName() {
        return userName;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getPostTopic() {
        return postTopic;
    }

    //Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostTopic(String postTopic) {
        this.postTopic = postTopic;
    }

    //Methods


    @Override
    public String toString() {
        return "Post{" +
                "userName='" + userName + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postTopic='" + postTopic + '\'' +
                '}';
    }
}
