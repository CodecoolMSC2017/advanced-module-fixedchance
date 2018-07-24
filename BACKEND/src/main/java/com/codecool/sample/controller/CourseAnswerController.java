package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseAnswer;
import com.codecool.sample.service.CourseAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseAnswerController extends AbstractController {

    @RequestMapping("/courses/answers")
    public List<CourseAnswer> getAll() throws SQLException {
        return answerService.getAnswers();
    }

    @RequestMapping("/courses/answers/{id}")
    public CourseAnswer getOne(@PathVariable("id") Integer id) {
        return answerService.getAnswerById(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions/{question_id}/answers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @PathVariable("question_id") Integer questionId, @RequestBody CourseAnswer answer) {
        answerService.addNewAnswer(courseId, questionId, answer);
    }
}
