package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="posts", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends AbstractModel{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "post_users",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "voter_id")})
    private Set<User> users = new HashSet<>();

    @NotNull
    private String userName;

    private String postContent;

    private Integer rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private Set<PostTopic> topics = new HashSet<>();

    public Post() {}

    //Getters

    public Set<User> getUsers() {
        return users;
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

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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
