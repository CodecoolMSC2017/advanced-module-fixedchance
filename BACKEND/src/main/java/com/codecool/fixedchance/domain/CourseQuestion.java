package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="course_questions", schema="public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseQuestion extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    private String question;

    private String questionType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courseQuestion")
    private Set<CourseAnswer> answers = new HashSet<>();

    public CourseQuestion() {}

    // Getters
    public String getQuestionType() {
        return questionType;
    }

    public Set<CourseAnswer> getAnswers() {
        return answers;
    }

    public Course getCourse() {
        return course;
    }

    public String getQuestion() {
        return question;
    }

    // Setters
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setAnswers(Set<CourseAnswer> answers) {
        this.answers = answers;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAll(CourseQuestion question) {
        this.question = question.getQuestion();
    }

    // Methods
    @Override
    public String toString() {
        return "CourseQuestion{" +
                "course=" + course +
                ", question='" + question + '\'' +
                '}';
    }
}
