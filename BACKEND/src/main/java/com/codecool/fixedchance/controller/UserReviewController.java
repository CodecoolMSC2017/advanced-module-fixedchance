package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.UserReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserReviewController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(UserReviewController.class);

    @RequestMapping(path = "/reviews/{id}")
    public UserReview getOne(@PathVariable("id") Integer id) {
        logger.info("returning review with id {}", id);
        return userReviewService.getOne(id);
    }

    @RequestMapping(path = "/reviews")
    public List<UserReview> getAll() {
        logger.info("returning all reviews");
        return userReviewService.getAll();
    }

    @RequestMapping(path = "/reviews/reviewer/{reviewer_id}")
    public UserReview getByReviewerId(@PathVariable("reviewer_id") Integer id) {
        logger.info("returning reviews of reviewer with id {}", id);
        return userReviewService.getByReviewerId(id);
    }

    @RequestMapping(path = "/reviews/reviewed/{reviewed_id}")
    public UserReview getByReviewedId(@PathVariable("reviewed_id") Integer id) {
        logger.info("returning reviews of reviewed with id {}", id);
        return userReviewService.getByReviewedId(id);
    }

    @RequestMapping(path = "/reviews",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody UserReview review) {
        logger.info("creating {}", review);
        userReviewService.add(review);
    }

    @RequestMapping(path = "/reviews/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting review with id {}", id);
        userReviewService.delete(id);
    }
}
