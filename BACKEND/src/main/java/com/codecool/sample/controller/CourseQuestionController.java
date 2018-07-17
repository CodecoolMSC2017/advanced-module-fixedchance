package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseQuestion;
import com.codecool.sample.service.CourseQuestionService;
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
public class CourseQuestionController {

    private CourseQuestionService questionService;

    @Autowired
    public void setQuestionService(CourseQuestionService questionService) {
        this.questionService = questionService;
    }

    // Get all questions
    @RequestMapping("/courses/questions")
    public List<CourseQuestion> getAllQuestions() {
        return questionService.getQuestions();
    }

    // Get question by its ID
    @RequestMapping("/courses/questions/id")
    public Optional<CourseQuestion> getQuestionById(Integer id) {
        return questionService.getQuestonById(id);
    }

    // Add question to database
    @RequestMapping(path = "/courses/questions/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseQuestion question) {
        try {
            questionService.addNewQuestion(question);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
