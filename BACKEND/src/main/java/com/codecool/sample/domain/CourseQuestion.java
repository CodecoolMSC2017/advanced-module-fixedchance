package com.codecool.sample.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="course_questions", schema="public")
public class CourseQuestion extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    @NotNull
    private Course course;

    private String question;

    public CourseQuestion() {}

    // Getters
    public Course getCourse() {
        return course;
    }

    public String getQuestion() {
        return question;
    }

    // Setters
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
