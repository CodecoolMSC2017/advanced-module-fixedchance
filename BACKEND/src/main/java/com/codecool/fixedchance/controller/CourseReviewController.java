package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseReview;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseReviewController extends AbstractController {

    @RequestMapping("/courses/reviews")
    public List<CourseReview> getAll() {
        return reviewService.getAll();
    }

    @RequestMapping("/courses/reviews/{id}")
    public CourseReview getOne(@PathVariable("id") Integer id) {
        return reviewService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/reviews",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseReview review) {
        reviewService.add(courseId, review);
    }

    @RequestMapping(path = "/courses/reviews/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseReview review) {
        reviewService.update(id, review);
    }

    @RequestMapping(path = "/courses/reviews/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        reviewService.delete(id);
    }

    @RequestMapping(path = "/courses/{course_id}/reviews",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<CourseReview> findByCourse(@PathVariable("course_id") Integer courseId) {
        return reviewService.findByCourse(courseId);
    }
}
