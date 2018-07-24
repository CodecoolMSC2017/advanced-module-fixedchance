package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseReview;
import com.codecool.sample.repository.CourseRepository;
import com.codecool.sample.repository.CourseReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public final class CourseReviewService extends AbstractService {

    public List<CourseReview> getReviews() {
        return reviewRepository.findAll();
    }

    public CourseReview getReviewById(Integer id) {
        return reviewRepository.getOne(id);
    }

    public void addNewReview(Integer courseId, CourseReview review) {
        Course course = courseRepository.getOne(courseId);
        review.setCourse(course);
        reviewRepository.save(review);
    }
}
