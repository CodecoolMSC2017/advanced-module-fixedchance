package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.PostComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostCommentController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(PostCommentController.class);

    @RequestMapping(path = "/comments",
            method = RequestMethod.GET)
    public List<PostComment> getAll() {
        logger.info("returning all comments");
        return postCommentService.getAll();
    }

    @RequestMapping(path = "/comments/{comment_id}")
    public PostComment getOne(@PathVariable("comment_id") Integer id) {
        logger.info("returning comment with id {}", id);
        return postCommentService.getOne(id);
    }

    @RequestMapping(path = "/comments/{post_id}",
            method = RequestMethod.POST,
            consumes = {"application/json"})
    public void add(@RequestBody PostComment comment, @PathVariable("post_id") Integer postId) {
        logger.info("creating comment for post with id {}, {}", postId, comment);
        postCommentService.add(comment, postId);
    }

    @RequestMapping(path = "/comments/{comment_id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("comment_id") Integer id) {
        logger.info("deleting comment with id {}", id);
        postCommentService.delete(id);
    }
}
