package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController extends AbstractController {

    @RequestMapping("/posts")
    public List<Post> getAll() { return postService.getAll();}
}
