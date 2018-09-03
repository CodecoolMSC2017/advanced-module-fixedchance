package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseVideo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseVideoController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CourseVideoController.class);

    @RequestMapping("/courses/videos")
    public List<CourseVideo> getAll() {
        logger.info("returning all videos");
        return videoService.getAll();
    }

    @RequestMapping("/courses/videos/{id}")
    public CourseVideo getOne(@PathVariable("id") Integer id) {
        logger.info("returning video with id {}", id);
        return videoService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/videos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseVideo video) {
        logger.info("creating video for course with id {}, {}", courseId, video);
        videoService.add(courseId, video);
    }

    @RequestMapping(path = "/courses/videos/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseVideo video) {
        logger.info("updating video with id {} to {}", id, video);
        videoService.update(id, video);
    }

    @RequestMapping(path = "/courses/videos/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting video with id {}", id);
        videoService.delete(id);
    }

    @RequestMapping(path = "/courses/{course_id}/videos",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<CourseVideo> findByCourse(@PathVariable("course_id") Integer courseId) {
        logger.info("returning videos of course with id {}", courseId);
        return videoService.findByCourse(courseId);
    }
}
