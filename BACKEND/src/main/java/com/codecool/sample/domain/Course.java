package com.codecool.sample.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="courses", schema="public")
public class Course extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId")
    @NotNull
    private User teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name = "course_student",
                joinColumns = {@JoinColumn(name = "course_id")},
                inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<User> students = new HashSet<>();

    private String name;

    private boolean isValidated;

    public Course() {
    }

    // Getters
    public Set<User> getStudents() {
        return students;
    }

    public User getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public boolean isValidated() {
        return isValidated;
    }

    // Setters
    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }
}
