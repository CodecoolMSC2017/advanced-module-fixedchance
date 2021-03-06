package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="course_topics", schema="public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseTopic extends AbstractModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @NotNull
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

    public void setAll(CourseTopic courseTopic) {
        this.name = courseTopic.getName();
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
