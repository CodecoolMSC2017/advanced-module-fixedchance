package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTopicRepository extends JpaRepository<CourseTopic, Integer> {

    List<CourseTopic> findAll();

    List<CourseTopic> findByCourse(Course course);
}
