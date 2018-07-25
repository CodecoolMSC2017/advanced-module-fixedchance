package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseQuestionRepository extends JpaRepository<CourseQuestion, Integer> {

    List<CourseQuestion> findAll();

    List<CourseQuestion> findByCourse(Course course);
}
