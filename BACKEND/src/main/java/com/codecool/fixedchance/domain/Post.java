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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull
    private SimpleUser user;

    private String userName;

    private String postContent;

    private Integer rating;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "post")
    private List<PostTopic> topics = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "post")
    private List<PostComment> comments = new ArrayList<>();

    public Post() {}

    //Getters

    public List<PostComment> getComments() {
        return comments;
    }

    public String getUserName() {
        return userName;
    }

    public SimpleUser getUser() {
        return user;
    }

    public String getPostContent() {
        return postContent;
    }

    public List<PostTopic> getTopics() {
        return topics;
    }

    public Integer getRating() {
        return rating;
    }

    //Setters


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setTopics(List<PostTopic> topics) {
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
                "user=" + user +
                ", userName='" + userName + '\'' +
                ", postContent='" + postContent + '\'' +
                ", rating=" + rating +
                ", topics=" + topics +
                ", comments=" + comments +
                '}';
    }
}
