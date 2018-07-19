package com.codecool.sample.repository;

import com.codecool.sample.domain.CourseAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseAnswerRepository extends JpaRepository<CourseAnswer, Integer> {

    List<CourseAnswer> findAll();
}
