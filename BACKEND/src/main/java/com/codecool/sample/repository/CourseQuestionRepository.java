package com.codecool.sample.repository;

import com.codecool.sample.model.CourseQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseQuestionRepository extends CrudRepository<CourseQuestion, Integer> {

    List<CourseQuestion> findAll();
}
