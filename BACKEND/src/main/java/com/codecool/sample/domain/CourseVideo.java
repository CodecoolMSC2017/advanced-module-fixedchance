package com.codecool.sample.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="course_videos", schema="public")
public class CourseVideo extends AbstractModel {

    private Integer courseId;
    private String name;
    private String video;
    private String description;

    public CourseVideo() {}

    // Getters
    public Integer getCourseId() {
        return courseId;
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
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
}
