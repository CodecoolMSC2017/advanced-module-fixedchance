package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseVideo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseVideoController extends AbstractController {

    @RequestMapping("/courses/videos")
    public List<CourseVideo> getAll() {
        return videoService.getAll();
    }

    @RequestMapping("/courses/videos/{id}")
    public CourseVideo getOne(@PathVariable("id") Integer id) {
        return videoService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/videos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseVideo video) {
            videoService.add(courseId, video);
    }

    @RequestMapping(path = "/courses/videos/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseVideo video) {
        videoService.update(id, video);
    }

    @RequestMapping(path = "/courses/videos/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        videoService.delete(id);
    }
}
