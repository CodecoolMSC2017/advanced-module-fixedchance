package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseTopic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseTopicController extends AbstractController {

    @RequestMapping("/courses/topics")
    public List<CourseTopic> getAll() {
        return topicService.getAll();
    }

    @RequestMapping("/courses/topics/{id}")
    public CourseTopic getOne(@PathVariable("id") Integer id) {
        return topicService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/topics",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseTopic topic) {
        topicService.add(courseId, topic);
    }

    @RequestMapping(path = "/courses/topics/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseTopic topic) {
        topicService.update(id, topic);
    }

    @RequestMapping(path = "/courses/topics/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        topicService.delete(id);
    }

    @RequestMapping(path = "/courses/{course_id}/topics",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<CourseTopic> findByCourse(@PathVariable("course_id") Integer courseId) {
        return topicService.findByCourse(courseId);
    }
}