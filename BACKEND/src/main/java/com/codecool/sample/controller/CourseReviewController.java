package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseReview;
import com.codecool.sample.service.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseReviewController {

    private CourseReviewService reviewService;

    @Autowired
    public void setReviewService(CourseReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Get all reviews
    @RequestMapping("/courses/reviews")
    public List<CourseReview> getAllReviews() {
        return reviewService.getReviews();
    }

    // Get review by its ID
    @RequestMapping("/courses/reviews/id")
    public Optional<CourseReview> getReviewById(Integer id) {
        return reviewService.getReviewById(id);
    }

    // Add review to database
    @RequestMapping(path = "/courses/reviews/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseReview review) {
        try {
            reviewService.addNewReview(review);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
