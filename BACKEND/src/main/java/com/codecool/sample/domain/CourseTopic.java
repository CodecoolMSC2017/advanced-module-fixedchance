package com.codecool.sample.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="course_topics", schema="public")
public class CourseTopic extends AbstractModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @NotNull
    @JsonBackReference
    private Course course;
    private String name;

    public CourseTopic() {}

    // Getters
    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods
    @Override
    public String toString() {
        return "CourseTopic{" +
                "course=" + course +
                ", name='" + name + '\'' +
                '}';
    }
}
