package com.codecool.sample.repository;

import com.codecool.sample.model.CourseAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseAnswerRepository extends CrudRepository<CourseAnswer, Integer> {

    List<CourseAnswer> findAll();
}
