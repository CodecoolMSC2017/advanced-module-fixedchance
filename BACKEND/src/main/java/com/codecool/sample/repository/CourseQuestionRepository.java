package com.codecool.sample.repository;

import com.codecool.sample.domain.CourseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseQuestionRepository extends JpaRepository<CourseQuestion, Integer> {

    List<CourseQuestion> findAll();
}
