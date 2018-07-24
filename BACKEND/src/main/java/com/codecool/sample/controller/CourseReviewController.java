package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseReview;
import com.codecool.sample.service.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/courses/reviews/{id}")
    public CourseReview getReviewById(@PathVariable("id") Integer id) {
        return reviewService.getReviewById(id);
    }

    // Add review to database
    @RequestMapping(path = "/courses/{course_id}/reviews",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseReview review) {
        reviewService.addNewReview(courseId, review);
    }
}
