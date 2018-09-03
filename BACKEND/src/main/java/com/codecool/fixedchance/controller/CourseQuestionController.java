package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseQuestionController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CourseQuestionController.class);

    @RequestMapping("/courses/questions")
    public List<CourseQuestion> getAll() {
        logger.info("returning all questions");
        return questionService.getAll();
    }

    @RequestMapping("/courses/questions/{id}")
    public CourseQuestion getOne(@PathVariable("id") Integer id) {
        logger.info("returning question with id {}", id);
        return questionService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Integer add(@RequestBody CourseQuestion question, @PathVariable("course_id") int courseId) {
        logger.info("creating question for course with id {}, {}", courseId, question);
        questionService.add(courseId, question);
        return question.getId();
    }

    @RequestMapping(path = "/courses/questions/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseQuestion question) {
        logger.info("updating question with id {} to {}", id, question);
        questionService.update(id, question);
    }

    @RequestMapping(path = "/courses/questions/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting question with id {}", id);
        questionService.delete(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<CourseQuestion> findByCourse(@PathVariable("course_id") Integer courseId) {
        logger.info("returning all questions with course id {}", courseId);
        return questionService.findByCourse(courseId);
    }
}
