package com.codecool.sample.repository;

import com.codecool.sample.domain.CourseReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewRepository extends CrudRepository<CourseReview, Integer> {

    List<CourseReview> findAll();
}
