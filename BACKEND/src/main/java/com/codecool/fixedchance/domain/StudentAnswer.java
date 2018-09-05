package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "student_answers")
public class StudentAnswer extends AbstractModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private CourseQuestion question;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id")
    private CourseAnswer answer;

    // Getters
    public User getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public CourseQuestion getQuestion() {
        return question;
    }

    public CourseAnswer getAnswer() {
        return answer;
    }

    // Setters
    public void setStudent(User student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setQuestion(CourseQuestion question) {
        this.question = question;
    }

    public void setAnswer(CourseAnswer answer) {
        this.answer = answer;
    }

    public void setAll(User student, Course course, CourseQuestion question, CourseAnswer answer) {
        this.student = student;
        this.course = course;
        this.question = question;
        this.answer = answer;
    }
    // Methods
    @Override
    public String toString() {
        return "StudentAnswer{" +
                "student=" + student +
                ", course=" + course +
                ", question=" + question +
                ", answer=" + answer +
                '}';
    }
}
