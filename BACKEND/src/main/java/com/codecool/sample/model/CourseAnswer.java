package com.codecool.sample.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="course_answers", schema="public")
public class CourseAnswer extends AbstractModel {

    private String answer;
    private Integer questionId;
    private boolean isRight;

    public CourseAnswer() {}

    // Getters
    public String getAnswer() {
        return answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public boolean isRight() {
        return isRight;
    }

    // Setters
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
