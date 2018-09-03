package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseReviewController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CourseReviewController.class);

    @RequestMapping("/courses/reviews")
    public List<CourseReview> getAll() {
        logger.info("returning all reviews");
        return reviewService.getAll();
    }

    @RequestMapping("/courses/reviews/{id}")
    public CourseReview getOne(@PathVariable("id") Integer id) {
        logger.info("returning review with id {}", id);
        return reviewService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/reviews",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @RequestBody CourseReview review) {
        logger.info("creating review for course {}, {}", courseId, review);
        reviewService.add(courseId, review);
    }

    @RequestMapping(path = "/courses/reviews/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseReview review) {
        logger.info("updating review with id {} to {}", id, review);
        reviewService.update(id, review);
    }

    @RequestMapping(path = "/courses/reviews/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting review with id {}", id);
        reviewService.delete(id);
    }

    @RequestMapping(path = "/courses/{course_id}/reviews",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<CourseReview> findByCourse(@PathVariable("course_id") Integer courseId) {
        logger.info("returning all questions of course {}", courseId);
        return reviewService.findByCourse(courseId);
    }
}
