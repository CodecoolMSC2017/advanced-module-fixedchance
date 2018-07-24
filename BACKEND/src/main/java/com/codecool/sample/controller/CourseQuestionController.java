package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseQuestion;
import com.codecool.sample.service.CourseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CourseQuestionController extends AbstractController {

    @RequestMapping("/courses/questions")
    public List<CourseQuestion> getAll() {
        return questionService.getQuestions();
    }

    @RequestMapping("/courses/questions/{id}")
    public CourseQuestion getOne(@PathVariable("id") Integer id) {
        return questionService.getQuestionById(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseQuestion question, @PathVariable("course_id") int courseId) {
        questionService.addNewQuestion(courseId, question);
    }
}
