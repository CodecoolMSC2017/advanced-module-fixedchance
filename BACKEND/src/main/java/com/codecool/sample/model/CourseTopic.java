package com.codecool.sample.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="course_topics", schema="public")
public class CourseTopic extends AbstractModel {

    private int courseId;
    private String name;

    public CourseTopic() {}

    // Getters
    public int getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
