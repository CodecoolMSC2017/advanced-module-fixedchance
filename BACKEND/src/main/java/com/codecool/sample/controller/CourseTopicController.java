package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseTopic;
import com.codecool.sample.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseTopicController {

    private CourseTopicService topicService;

    @Autowired
    public void setTopicService(CourseTopicService topicService) {
        this.topicService = topicService;
    }

    // Get all topics
    @RequestMapping("/courses/topics")
    public List<CourseTopic> getAllTopics() {
        return topicService.getTopics();
    }

    // Get topic by its ID
    @RequestMapping("/courses/topics/{id}")
    public Optional<CourseTopic> getTopicById(@PathVariable("id") Integer id) {
        return topicService.getTopicById(id);
    }

    // Add topic to database
    @RequestMapping(path = "/courses/topics",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseTopic topic) {
        topicService.addNewTopic(topic);
    }

}
