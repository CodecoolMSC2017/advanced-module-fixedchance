package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name="post_topics", schema="public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostTopic extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    private String name;

    public PostTopic() {
    }

    // Getters
    public Post getPost() {
        return post;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setPost(Post post) {
        this.post = post;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods
    @Override
    public String toString() {
        return "PostTopic{" +
                "post=" + post +
                ", name='" + name + '\'' +
                '}';
    }
}
