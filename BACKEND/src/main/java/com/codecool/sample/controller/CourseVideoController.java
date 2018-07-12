package com.codecool.sample.controller;

import com.codecool.sample.model.CourseVideo;
import com.codecool.sample.service.CourseVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/courses/videos/id")
    public Optional<CourseVideo> getVideoById(Integer id) {
        return videoService.getVideoById(id);
    }

    // Add video to database
    @RequestMapping(path = "/courses/videos/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseVideo video) {
        try {
            videoService.addNewVideo(video);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
