package com.codecool.sample.controller;

import com.codecool.sample.model.CourseAnswer;
import com.codecool.sample.service.CourseAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/courses/answers/id")
    public Optional<CourseAnswer> getAnswerById(Integer id) {
        return answerService.getAnswerById(id);
    }

    @RequestMapping(path = "/courses/answers/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseAnswer answer) {
        try {
            answerService.addNewAnswer(answer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
