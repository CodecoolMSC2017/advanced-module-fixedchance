package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PostController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @RequestMapping(path = "/posts",
            method = RequestMethod.GET)
    public List<Post> getAll() {
        logger.info("returning all posts");
        return postService.getAll();
    }

    @RequestMapping(path = "/posts/{post_id}")
    public Post getOne(@PathVariable("post_id") Integer id) {
        logger.info("returning post with id {}", id);
        return postService.getOne(id);
    }

    @RequestMapping(path = "/posts",
            method = RequestMethod.POST,
            consumes = {"application/json"})
    public Post add(@RequestBody Post post) {
        logger.info("creating post {}", post);
        return postService.add(post);
    }

    @RequestMapping(path = "/posts/{id}",
            method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting post with id {}", id);
        postCommentService.deleteByPost(id);
        postVoteService.delete(id);
        postService.delete(id);
    }

    @RequestMapping(path = "/posts/update/{post_id}/{rating}",
            method = RequestMethod.PUT)
    public void update(@PathVariable("post_id") Integer id, @PathVariable("rating") Boolean rating) {
        logger.info("updating post rating with id {}", id);
        postService.update(id, rating);
    }

}
