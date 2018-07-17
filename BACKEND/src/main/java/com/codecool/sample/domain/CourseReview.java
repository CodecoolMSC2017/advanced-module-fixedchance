package com.codecool.sample.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="course_reviews", schema="public")
public class CourseReview extends AbstractModel {

    private Integer courseId;
    private Integer rating;
    private String description;

    public CourseReview() {}

    // Getters
    public Integer getCourseId() {
        return courseId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
