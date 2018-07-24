package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseAnswer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseAnswerController extends AbstractController {

    @RequestMapping("/courses/answers")
    public List<CourseAnswer> getAll() {
        return answerService.getAll();
    }

    @RequestMapping("/courses/answers/{id}")
    public CourseAnswer getOne(@PathVariable("id") Integer id) {
        return answerService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions/{question_id}/answers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @PathVariable("question_id") Integer questionId, @RequestBody CourseAnswer answer) {
        answerService.add(courseId, questionId, answer);
    }

    @RequestMapping(path = "/courses/answers/{id}",
                   method = RequestMethod.PUT,
                   consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseAnswer answer) {
        answerService.update(id, answer);
    }

    @RequestMapping(path = "/courses/answers/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        answerService.delete(id);
    }
}
