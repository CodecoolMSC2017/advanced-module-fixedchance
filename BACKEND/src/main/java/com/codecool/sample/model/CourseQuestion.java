package com.codecool.sample.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="course_questions", schema="public")
public class CourseQuestion extends AbstractModel {

    private Integer courseId;
    private String question;

    public CourseQuestion() {}

    // Getters
    public Integer getCourseId() {
        return courseId;
    }

    public String getQuestion() {
        return question;
    }

    // Setters
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
