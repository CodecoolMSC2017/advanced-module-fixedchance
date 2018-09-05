package com.codecool.fixedchance.domain;

public class AnswerDTO {

    private int studentId;
    private int courseId;
    private int questionId;
    private int answerId;

    public AnswerDTO() {
    }

    public AnswerDTO(int studentId, int courseId, int questionId, int answerId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    // Setters
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    // Methods
    @Override
    public String toString() {
        return "AnswerDTO{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                '}';
    }
}
