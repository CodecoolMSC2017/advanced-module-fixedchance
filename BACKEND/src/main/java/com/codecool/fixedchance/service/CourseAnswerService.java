package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseAnswer;
import com.codecool.fixedchance.domain.CourseQuestion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseAnswerService extends AbstractService {

    public List<CourseAnswer> getAll() {
        return answerRepository.findAll();
    }

    public CourseAnswer getOne(Integer id) {
        return answerRepository.getOne(id);
    }

    public void add(Integer courseId, Integer questionId, CourseAnswer answer) {
        Course course = courseRepository.getOne(courseId);
        CourseQuestion question = questionRepository.getOne(questionId);
        question.setCourse(course);
        answer.setCourseQuestion(question);
        answerRepository.save(answer);
    }

    public void update(Integer id, CourseAnswer answer) {
        CourseAnswer ans = answerRepository.getOne(id);
        ans.setAll(answer);
        answerRepository.save(ans);
    }

    public void delete(Integer id) {
        answerRepository.deleteById(id);
    }

    public List<CourseAnswer> findAnswers(Integer questionId) {
        CourseQuestion question = questionRepository.getOne(questionId);
        return answerRepository.findByCourseQuestion(question);
    }
}
