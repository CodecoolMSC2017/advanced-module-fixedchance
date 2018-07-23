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
public class CourseAnswerController {

    private CourseAnswerService answerService;

    @Autowired
    public void setAnserService(CourseAnswerService ansService) {
        this.answerService = ansService;
    }

    @RequestMapping("/courses/answers")
    public List<CourseAnswer> getAllAnswer() throws SQLException {
        return answerService.getAnswers();
    }

    @RequestMapping("/courses/answers/{id}")
    public Optional<CourseAnswer> getAnswerById(@PathVariable("id") Integer id) {
        return answerService.getAnswerById(id);
    }

    @RequestMapping(path = "/courses/answers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseAnswer answer) {
        answerService.addNewAnswer(answer);
    }
}
