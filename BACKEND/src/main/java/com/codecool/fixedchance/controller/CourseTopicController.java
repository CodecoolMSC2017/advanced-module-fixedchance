package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseTopicController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CourseTopicController.class);


    @RequestMapping("/courses/topics")
    public List<CourseTopic> getAll() {
        logger.info("returning all topics");
        return topicService.getAll();
    }

    @RequestMapping("/courses/topics/{id}")
    public CourseTopic getOne(@PathVariable("id") Integer id) {
        logger.info("returning topic with id {}", id);
        return topicService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/topics",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseTopic topic) {
        logger.info("creating topic for course with id {}, {}", courseId, topic);
        topicService.add(courseId, topic);
    }

    @RequestMapping(path = "/courses/topics/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseTopic topic) {
        logger.info("updating topic with id {} to {}", id, topic);
        topicService.update(id, topic);
    }

    @RequestMapping(path = "/courses/topics/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting topic with id {}", id);
        topicService.delete(id);
    }

    @RequestMapping(path = "/courses/{course_id}/topics",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<CourseTopic> findByCourse(@PathVariable("course_id") Integer courseId) {
        logger.info("returning all topics of course with id {}", courseId);
        return topicService.findByCourse(courseId);
    }
}
