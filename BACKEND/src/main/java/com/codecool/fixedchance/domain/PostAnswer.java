package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="comment_answers", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostAnswer extends AbstractModel {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private SimpleUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private PostComment comment;

    @Column(name = "answer_text")
    private String answer;

    public PostAnswer() {
    }


    // Getters
    public SimpleUser getUser() {
        return user;
    }

    public PostComment getComment() {
        return comment;
    }

    public String getAnswer() {
        return answer;
    }

    // Setters
    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public void setComment(PostComment comment) {
        this.comment = comment;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Methods
    @Override
    public String toString() {
        return "PostAnswer{" +
                "user=" + user +
                ", comment=" + comment +
                ", answer='" + answer + '\'' +
                '}';
    }
}
