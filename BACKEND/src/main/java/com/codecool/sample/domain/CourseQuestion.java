package com.codecool.sample.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="course_questions", schema="public")
public class CourseQuestion extends AbstractModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @NotNull
    @JsonBackReference
    private Course course;

    private String question;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseQuestion")
    @JsonManagedReference
    private Set<CourseAnswer> answers = new HashSet<>();

    public CourseQuestion() {}

    // Getters
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
    public void setAnswers(Set<CourseAnswer> answers) {
        this.answers = answers;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setQuestion(String question) {
        this.question = question;
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
