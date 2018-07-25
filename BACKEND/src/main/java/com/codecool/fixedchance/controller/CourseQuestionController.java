package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseQuestion;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseQuestionController extends AbstractController {

    @RequestMapping("/courses/questions")
    public List<CourseQuestion> getAll() {
        return questionService.getAll();
    }

    @RequestMapping("/courses/questions/{id}")
    public CourseQuestion getOne(@PathVariable("id") Integer id) {
        return questionService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseQuestion question, @PathVariable("course_id") int courseId) {
        questionService.add(courseId, question);
    }

    @RequestMapping(path = "/courses/questions/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseQuestion question) {
        questionService.update(id, question);
    }

    @RequestMapping(path = "/courses/questions/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        questionService.delete(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<CourseQuestion> findByCourse(@PathVariable("course_id") Integer courseId) {
        return questionService.findByCourse(courseId);
    }
}
