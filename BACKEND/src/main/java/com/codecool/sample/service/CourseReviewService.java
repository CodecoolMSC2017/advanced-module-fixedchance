package com.codecool.sample.service;

import com.codecool.sample.domain.CourseReview;
import com.codecool.sample.repository.CourseReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CourseReviewService {

    @Autowired
    private CourseReviewRepository reviewRepository;

    public List<CourseReview> getReviews() {
        return reviewRepository.findAll();
    }

    public Optional<CourseReview> getReviewById(Integer id) {
        return reviewRepository.findById(id);
    }

    public void addNewReview(CourseReview review) throws SQLException {
        try {
            reviewRepository.save(review);
        } catch (Exception e) {
            throw new SQLException("Review already exists");
        }
    }
}
