package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="course_student", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseStudent implements Serializable {

    @EmbeddedId
    private CourseStudentIdentity courseStudentIdentity;

    @Column(insertable = false, updatable = false)
    private Integer courseId;

    @Column(insertable = false, updatable = false)
    private Integer studentId;

    // Getters
    public CourseStudentIdentity getCourseStudentIdentity() {
        return courseStudentIdentity;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    // Setters
    public void setCourseStudentIdentity(CourseStudentIdentity courseStudentIdentity) {
        this.courseStudentIdentity = courseStudentIdentity;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    // Methods

    @Override
    public String toString() {
        return "CourseStudent{" +
                "courseStudentIdentity=" + courseStudentIdentity +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}
