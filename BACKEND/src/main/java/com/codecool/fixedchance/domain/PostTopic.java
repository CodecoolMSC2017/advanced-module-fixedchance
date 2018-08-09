package com.codecool.fixedchance.domain;

import javax.persistence.*;

@Entity
@Table(name="post_topics", schema="public")
public class PostTopic extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
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
