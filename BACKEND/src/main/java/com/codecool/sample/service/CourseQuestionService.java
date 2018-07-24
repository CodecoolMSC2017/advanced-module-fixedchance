package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseQuestion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseQuestionService extends AbstractService {

    public List<CourseQuestion> getQuestions() {
        return questionRepository.findAll();
    }

    public CourseQuestion getQuestionById(Integer id) {
        return questionRepository.getOne(id);
    }

    public void addNewQuestion(int courseId, CourseQuestion question) {
        Course course = courseRepository.getOne(courseId);
        question.setCourse(course);
        questionRepository.save(question);
    }

    public void update(Integer id, CourseQuestion question) {
        CourseQuestion question1 = questionRepository.getOne(id);
        question1.setAll(question);
        questionRepository.save(question1);
    }

}
