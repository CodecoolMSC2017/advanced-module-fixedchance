package com.codecool.fixedchance.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class CourseStudentIdentity implements Serializable {

    @NotNull
    private Integer courseId;

    @NotNull
    private Integer studentId;

    //Getters
    public Integer getCourseId() {
        return courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    // Setters
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
