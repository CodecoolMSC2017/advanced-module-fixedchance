package com.codecool.sample.service;

import com.codecool.sample.model.CourseAnswer;
import com.codecool.sample.repository.CourseAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CourseAnswerService {

    @Autowired
    private CourseAnswerRepository answerRepository;

    public List<CourseAnswer> getAnswers() {
        return answerRepository.findAll();
    }

    public Optional<CourseAnswer> getAnswerById(Integer id) {
        return answerRepository.findById(id);
    }

    public void addNewAnswer(CourseAnswer answer) throws SQLException {
        try {
            answerRepository.save(answer);
        } catch (Exception e) {
            throw new SQLException("Answer already exists");
        }
    }
}
