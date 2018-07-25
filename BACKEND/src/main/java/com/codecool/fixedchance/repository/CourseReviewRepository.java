package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, Integer> {

    List<CourseReview> findAll();

    List<CourseReview> findByCourse(Course course);
}
