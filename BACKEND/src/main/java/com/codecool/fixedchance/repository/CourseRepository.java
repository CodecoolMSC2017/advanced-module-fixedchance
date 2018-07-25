package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findAll();

    List<Course> findByTeacher(User user);
}
