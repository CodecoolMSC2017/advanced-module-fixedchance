package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseTopic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseTopicController extends AbstractController {

    @RequestMapping("/courses/topics")
    public List<CourseTopic> getAll() {
        return topicService.getTopics();
    }

    @RequestMapping("/courses/topics/{id}")
    public CourseTopic getOne(@PathVariable("id") Integer id) {
        return topicService.getTopicById(id);
    }

    @RequestMapping(path = "/courses/{course_id}/topics",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseTopic topic) {
        topicService.addNewTopic(courseId, topic);
    }

    @RequestMapping(path = "/courses/topics/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseTopic topic) {
        topicService.update(id, topic);
    }
}
