package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="schedules", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Schedule extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    @NotNull
    @JsonBackReference
    private User teacher;

    private Integer studentId;
    private Integer startTime;
    private Date date;

    public Schedule() {
    }

    // Getters
    public User getTeacher() {
        return teacher;
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
    public void setTeacher(User teacher) {
        this.teacher = teacher;
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

    public void setAll(Schedule schedule) {
        this.startTime = schedule.getStartTime();
    }
    // Methods
    @Override
    public String toString() {
        return "Schedule{" +
                "teacher=" + teacher +
                ", studentId=" + studentId +
                ", startTime=" + startTime +
                ", date=" + date +
                '}';
    }
}
