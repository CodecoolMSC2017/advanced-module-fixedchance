package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "post_users", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostVote implements Serializable{

    @EmbeddedId
    private PostUserIdentity postUserIdentity;

    @Column(insertable = false, updatable = false)
    private Integer postId;

    @Column(insertable = false, updatable = false)
    private Integer voterId;

    private Boolean vote;

    //Getters

    public PostUserIdentity getPostUserIdentity() {
        return postUserIdentity;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getVoterId() {
        return voterId;
    }

    public Boolean getVote() {
        return vote;
    }

    //Setters

    public void setPostUserIdentity(PostUserIdentity postUserIdentity) {
        this.postUserIdentity = postUserIdentity;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "PostVote{" +
                "postUserIdentity=" + postUserIdentity +
                ", postId=" + postId +
                ", voterId=" + voterId +
                ", vote=" + vote +
                '}';
    }
}
