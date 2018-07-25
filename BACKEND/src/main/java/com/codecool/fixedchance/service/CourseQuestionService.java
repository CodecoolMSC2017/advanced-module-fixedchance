package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseQuestion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseQuestionService extends AbstractService {

    public List<CourseQuestion> getAll() {
        return questionRepository.findAll();
    }

    public CourseQuestion getOne(Integer id) {
        return questionRepository.getOne(id);
    }

    public void add(int courseId, CourseQuestion question) {
        Course course = courseRepository.getOne(courseId);
        question.setCourse(course);
        questionRepository.save(question);
    }

    public void update(Integer id, CourseQuestion question) {
        CourseQuestion question1 = questionRepository.getOne(id);
        question1.setAll(question);
        questionRepository.save(question1);
    }

    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }

}
