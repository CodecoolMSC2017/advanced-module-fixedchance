package com.codecool.sample.service;

import com.codecool.sample.model.CourseQuestion;
import com.codecool.sample.repository.CourseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CourseQuestionService {

    @Autowired
    private CourseQuestionRepository questionRepository;

    public List<CourseQuestion> getQuestions() {
        return questionRepository.findAll();
    }

    public Optional<CourseQuestion> getQuestonById(Integer id) {
        return questionRepository.findById(id);
    }

    public void addNewQuestion(CourseQuestion question) throws SQLException {
        try {
            questionRepository.save(question);
        } catch (Exception e) {
            throw new SQLException("Question already exists");
        }
    }
}
