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
            method = RequestMethod.POST)
    public void add(@RequestBody Post post) {
        postService.add(post);
    }

    @RequestMapping(path = "/posts/{id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) { postService.delete(id);}
}
