package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseQuestion;
import com.codecool.sample.repository.CourseQuestionRepository;
import com.codecool.sample.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CourseQuestionService {

    @Autowired
    private CourseQuestionRepository questionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseQuestion> getQuestions() {
        return questionRepository.findAll();
    }

    public Optional<CourseQuestion> getQuestonById(Integer id) {
        return questionRepository.findById(id);
    }

    public void addNewQuestion(int courseId, CourseQuestion question) {
        Course course = courseRepository.getOne(courseId);
        question.setCourse(course);
        questionRepository.save(question);
    }
}
