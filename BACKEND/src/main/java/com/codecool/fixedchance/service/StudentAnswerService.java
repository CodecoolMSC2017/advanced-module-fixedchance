package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.StudentAnswer;
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

    public void add(Integer studentAnserId, StudentAnswer studentAnswer) { studentAnswerRepository.save(studentAnswer);}

    public void delete(Integer id) { studentAnswerRepository.deleteById(id);}
}
