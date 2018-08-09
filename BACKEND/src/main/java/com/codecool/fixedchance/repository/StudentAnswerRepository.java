package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Integer> {

    List<StudentAnswer> findAll();
}
