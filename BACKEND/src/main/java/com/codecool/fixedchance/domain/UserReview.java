package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_reviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserReview extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewer_id")
    private SimpleUser reviewer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewed_id")
    private SimpleUser reviewed;

    private String description;
    @Column(name = "pov_one")
    private Integer povOne;
    @Column(name = "pov_two")
    private Integer powTwo;
    @Column(name = "pov_three")
    private Integer povThree;
    private Date date = new Date();

    public UserReview() {
    }

    // Getters
    public SimpleUser getReviewed() {
        return reviewed;
    }

    public SimpleUser getReviewer() {
        return reviewer;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Integer getPovOne() {
        return povOne;
    }

    public Integer getPowTwo() {
        return powTwo;
    }

    public Integer getPovThree() {
        return povThree;
    }

    // Setters
    public void setReviewer(SimpleUser reviewer) {
        this.reviewer = reviewer;
    }

    public void setReviewed(SimpleUser reviewed) {
        this.reviewed = reviewed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPovOne(Integer povOne) {
        this.povOne = povOne;
    }

    public void setPowTwo(Integer powTwo) {
        this.powTwo = powTwo;
    }

    public void setPovThree(Integer povThree) {
        this.povThree = povThree;
    }

// Methods
    @Override
    public String toString() {
        return "UserReview{" +
                "reviewer=" + reviewer +
                ", reviewed=" + reviewed +
                ", description='" + description + '\'' +
                ", povOne=" + povOne +
                ", powTwo=" + powTwo +
                ", povThree=" + povThree +
                ", date=" + date +
                '}';
    }
}
