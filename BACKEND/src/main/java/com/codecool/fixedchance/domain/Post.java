package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="posts", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends AbstractModel{

    @NotNull
    private String userName;

    private String postContent;

    private Integer rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private Set<PostTopic> topics = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<PostComment> comments = new ArrayList<>();

    public Post() {}

    //Getters

    public List<PostComment> getComments() {
        return comments;
    }

    public String getUserName() {
        return userName;
    }

    public String getPostContent() {
        return postContent;
    }

    public Set<PostTopic> getTopics() {
        return topics;
    }

    public Integer getRating() {
        return rating;
    }

    //Setters


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setTopics(Set<PostTopic> topics) {
        this.topics = topics;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    //Methods
    @Override
    public String toString() {
        return "Post{" +
                "userName='" + userName + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postTopic='" + topics + '\'' +
                '}';
    }
}
