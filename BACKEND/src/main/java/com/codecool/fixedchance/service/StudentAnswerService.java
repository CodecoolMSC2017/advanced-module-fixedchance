package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentAnswerService extends AbstractService {

    public List<StudentAnswer> findAll() {
        return studentAnswerRepository.findAll();
    }

    public StudentAnswer getOne(Integer id) {
        return studentAnswerRepository.getOne(id);
    }

    public void add(AnswerDTO answerDTO) {
        User student = userRepository.getOne(answerDTO.getStudentId());
        Course course = courseRepository.getOne(answerDTO.getCourseId());
        CourseQuestion question = questionRepository.getOne(answerDTO.getQuestionId());
        CourseAnswer answer = answerRepository.getOne(answerDTO.getAnswerId());

        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setAll(student, course, question, answer);
        studentAnswerRepository.save(studentAnswer);
    }

    public List<StudentAnswer> findAllByCourseIdAndStudentId(Integer courseId, Integer studentId) {
        return studentAnswerRepository.findAllByCourseIdAndStudentId(courseId, studentId);
    }

    public void delete(Integer id) { studentAnswerRepository.deleteById(id);}
}
