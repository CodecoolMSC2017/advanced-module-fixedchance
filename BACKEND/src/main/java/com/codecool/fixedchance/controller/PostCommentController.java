package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.PostComment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostCommentController extends AbstractController {

    @RequestMapping(path = "/comments",
            method = RequestMethod.GET)
    public List<PostComment> getAll() {
        return postCommentService.getAll();
    }

    @RequestMapping(path = "/comments/{comment_id}")
    public PostComment getOne(@PathVariable("comment_id") Integer id) {
        return postCommentService.getOne(id);
    }

    @RequestMapping(path = "/comments/{post_id}",
            method = RequestMethod.POST,
            consumes = {"application/json"})
    public void add(@RequestBody PostComment comment, @PathVariable("post_id") Integer postId) {
        postCommentService.add(comment, postId);
    }

    @RequestMapping(path = "/comments/{comment_id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("comment_id") Integer id) { postCommentService.delete(id);}

    @RequestMapping(path = "/comment/{comment_id}",
            method = RequestMethod.PUT)
    public void updateComment(@PathVariable("comment_id") Integer id) { postCommentService.update(id);}
}
