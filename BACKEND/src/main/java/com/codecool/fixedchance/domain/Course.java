package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="courses", schema="public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId")
    @NotNull
    private User teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "course_student",
                joinColumns = {@JoinColumn(name = "course_id")},
                inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<User> students = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private Set<CourseQuestion> questions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private Set<CourseReview> reviews = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private Set<CourseVideo> videos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private Set<CourseTopic> topics = new HashSet<>();

    private String name;

    private boolean isValidated;

    public Course() {
    }

    // Getters
    public Set<CourseTopic> getTopics() {
        return topics;
    }

    public Set<CourseVideo> getVideos() {
        return videos;
    }

    public Set<CourseReview> getReviews() {
        return reviews;
    }

    public Set<CourseQuestion> getQuestions() {
        return questions;
    }

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
    public void setTopics(Set<CourseTopic> topics) {
        this.topics = topics;
    }

    public void setVideos(Set<CourseVideo> videos) {
        this.videos = videos;
    }

    public void setReviews(Set<CourseReview> reviews) {
        this.reviews = reviews;
    }

    public void setQuestions(Set<CourseQuestion> questions) {
        this.questions = questions;
    }

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

    //Methods
    @Override
    public String toString() {
        return "Course{" +
                "teacher=" + teacher +
                ", students=" + students +
                ", name='" + name + '\'' +
                ", isValidated=" + isValidated +
                '}';
    }
}
