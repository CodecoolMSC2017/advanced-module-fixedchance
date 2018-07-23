package com.codecool.sample.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="schedules", schema="public")
public class Schedule extends AbstractModel {

    private Integer teacherId;
    private Integer studentId;
    private Integer startTime;
    private Date date;

    public Schedule() {
    }

    // Getters
    public Integer getTeacherId() {
        return teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Date getDate() {
        return date;
    }

    // Setters

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
