package com.codecool.sample.controller;

import com.codecool.sample.domain.CourseReview;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseReviewController extends AbstractController {

    @RequestMapping("/courses/reviews")
    public List<CourseReview> getAll() {
        return reviewService.getReviews();
    }

    @RequestMapping("/courses/reviews/{id}")
    public CourseReview getOne(@PathVariable("id") Integer id) {
        return reviewService.getReviewById(id);
    }

    @RequestMapping(path = "/courses/{course_id}/reviews",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseReview review) {
        reviewService.addNewReview(courseId, review);
    }

    @RequestMapping(path = "/courses/reviews/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseReview review) {
        reviewService.update(id, review);
    }
}
