package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="course_videos", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseVideo extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    @NotNull
    @JsonBackReference(value = "course_videos.course")
    private Course course;
    private String name;
    private String video;
    private String description;

    public CourseVideo() {}

    // Getters
    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getVideo() {
        return video;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAll(CourseVideo courseVideo) {
        this.description = courseVideo.getDescription();
        this.name = courseVideo.getName();
        this.video = courseVideo.getVideo();
    }

    // Methods
    @Override
    public String toString() {
        return "CourseVideo{" +
                "courseId=" + course +
                ", name='" + name + '\'' +
                ", video='" + video + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
