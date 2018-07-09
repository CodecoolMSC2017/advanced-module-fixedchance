package com.codecool.sample.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="courses", schema="public")
public class Course extends AbstractModel {

    private Integer teacherId, studentId;
    private String name;
    private boolean isValidated;

    public Course() {
    }

    // Getters

    public Integer getTeacherId() {
        return teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public boolean isValidated() {
        return isValidated;
    }

    // Setters

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }
}
