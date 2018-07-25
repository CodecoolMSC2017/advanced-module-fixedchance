package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.CourseAnswer;
import com.codecool.fixedchance.domain.CourseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseAnswerRepository extends JpaRepository<CourseAnswer, Integer> {

    List<CourseAnswer> findAll();

    List<CourseAnswer> findByCourseQuestion(CourseQuestion question);
}
