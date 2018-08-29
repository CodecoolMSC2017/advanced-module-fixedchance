package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Post;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController extends AbstractController {

    @RequestMapping(path = "/posts",
            method = RequestMethod.GET)
    public List<Post> getAll() {
        return postService.getAll();
    }

    @RequestMapping(path = "/posts/{post_id}")
    public Post getOne(@PathVariable("post_id") Integer id) {
        return postService.getOne(id);
    }

    @RequestMapping(path = "/posts",
            method = RequestMethod.POST,
            consumes = {"application/json"})
    public Post add(@RequestBody Post post) {
        return postService.add(post);
    }

    @RequestMapping(path = "/posts/{id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) { postService.delete(id);}

    @RequestMapping(path = "/posts/update/up/{post_id}",
            method = RequestMethod.POST)
    public void update(@PathVariable("post_id") Integer id) { postService.update(id);}

    @RequestMapping(path = "/posts/update/down/{post_id}",
            method = RequestMethod.POST)
    public void decrementRating(@PathVariable("post_id") Integer id) { postService.decrement(id);}
}
