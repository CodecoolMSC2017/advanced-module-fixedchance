package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.UserReview;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserReviewController extends AbstractController {


    @RequestMapping(path = "/reviews/{id}")
    public UserReview getOne(@PathVariable("id") Integer id) {
        return userReviewService.getOne(id);
    }

    @RequestMapping(path = "/reviews")
    public List<UserReview> getAll() {
        return userReviewService.getAll();
    }

    @RequestMapping(path = "/reviews/reviewer/{reviewer_id}")
    public UserReview getByReviewerId(@PathVariable("reviewer_id") Integer id) {
        return userReviewService.getByReviewerId(id);
    }

    @RequestMapping(path = "/reviews/reviewed/{reviewed_id}")
    public UserReview getByReviewedId(@PathVariable("reviewed_id") Integer id) {
        return userReviewService.getByReviewedId(id);
    }

    @RequestMapping(path = "/reviews/delete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody UserReview review) {
        userReviewService.add(review);
    }

    @RequestMapping(path = "/reviews/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        userReviewService.delete(id);
    }
}
