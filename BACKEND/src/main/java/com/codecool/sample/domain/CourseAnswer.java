package com.codecool.sample.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="course_answers", schema="public")
public class CourseAnswer extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    @NotNull
    @JsonBackReference
    private CourseQuestion courseQuestion;

    private String answer;

    private boolean isRight;

    public CourseAnswer() {}

    // Getters
    public String getAnswer() {
        return answer;
    }

    public CourseQuestion getCourseQuestion() {
        return courseQuestion;
    }

    public boolean isRight() {
        return isRight;
    }

    // Setters
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCourseQuestion(CourseQuestion courseQuestion) {
        this.courseQuestion = courseQuestion;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    // Methods
    @Override
    public String toString() {
        return "CourseAnswer{" +
                "courseQuestion=" + courseQuestion +
                ", answer='" + answer + '\'' +
                ", isRight=" + isRight +
                '}';
    }
}
