package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseReview;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseReviewService extends AbstractService {

    public List<CourseReview> getAll() {
        return reviewRepository.findAll();
    }

    public CourseReview getOne(Integer id) {
        return reviewRepository.getOne(id);
    }

    public void add(Integer courseId, CourseReview review) {
        Course course = courseRepository.getOne(courseId);
        review.setCourse(course);
        reviewRepository.save(review);
    }

    public void update(Integer id, CourseReview review) {
        CourseReview review1 = reviewRepository.getOne(id);
        review1.setAll(review);
        reviewRepository.save(review1);
    }

    public void delete(Integer id) {
        reviewRepository.deleteById(id);
    }

    public List<CourseReview> findByCourse(Integer courseId) {
        Course course = courseRepository.getOne(courseId);
        return reviewRepository.findByCourse(course);
    }
}
