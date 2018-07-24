package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseAnswer;
import com.codecool.sample.domain.CourseQuestion;
import com.codecool.sample.repository.CourseAnswerRepository;
import com.codecool.sample.repository.CourseQuestionRepository;
import com.codecool.sample.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public final class CourseAnswerService extends AbstractService {

    public List<CourseAnswer> getAnswers() {
        return answerRepository.findAll();
    }

    public CourseAnswer getAnswerById(Integer id) {
        return answerRepository.getOne(id);
    }

    public void addNewAnswer(Integer courseId, Integer questionId, CourseAnswer answer) {
        Course course = courseRepository.getOne(courseId);
        CourseQuestion question = questionRepository.getOne(questionId);
        question.setCourse(course);
        answer.setCourseQuestion(question);
        answerRepository.save(answer);
    }
}
