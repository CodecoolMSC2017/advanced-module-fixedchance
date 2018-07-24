package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseVideo;
import com.codecool.sample.service.CourseVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseVideoController {

    private CourseVideoService videoService;

    @Autowired
    public void setVideoService(CourseVideoService videoService) {
        this.videoService = videoService;
    }

    // Get all videos
    @RequestMapping("/courses/videos")
    public List<CourseVideo> getAllVideos() {
        return videoService.getVideos();
    }

    // Get video by its ID
    @RequestMapping("/courses/videos/{id}")
    public CourseVideo getVideoById(@PathVariable("id") Integer id) {
        return videoService.getVideoById(id);
    }

    // Add video to database
    @RequestMapping(path = "/courses/{course_id}/videos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseVideo video) {
            videoService.addNewVideo(courseId, video);
    }
}
