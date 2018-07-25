package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseVideoRepository extends JpaRepository<CourseVideo, Integer> {

    List<CourseVideo> findAll();

    List<CourseVideo> findByCourse(Course course);
}
