package com.codecool.sample.repository;

import com.codecool.sample.domain.CourseTopic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTopicRepository extends CrudRepository<CourseTopic, Integer> {

    List<CourseTopic> findAll();
}
