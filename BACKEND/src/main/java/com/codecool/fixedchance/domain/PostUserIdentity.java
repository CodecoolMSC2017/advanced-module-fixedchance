package com.codecool.fixedchance.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class PostUserIdentity implements Serializable {

    @NotNull
    private Integer postId;

    @NotNull
    private Integer voterId;

    //Getters

    public Integer getPostId() {
        return postId;
    }

    public Integer getVoterId() {
        return voterId;
    }

    //Setters

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }
}
