package com.codecool.fixedchance.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="course_reviews", schema="public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseReview extends AbstractModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    @NotNull
    private User student;

    private Integer rating;
    private String description;

    public CourseReview() {}

    // Getters
    public User getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setStudent(User student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAll(CourseReview review) {
        this.rating = review.getRating();
        this.description = review.getDescription();
    }

    // Methods
    @Override
    public String toString() {
        return "CourseReview{" +
                "course=" + course +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
