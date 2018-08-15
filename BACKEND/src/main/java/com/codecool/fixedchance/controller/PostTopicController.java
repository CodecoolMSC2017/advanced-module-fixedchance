package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.PostTopic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostTopicController extends AbstractController {

    @RequestMapping(path = "/post-topics",
            method = RequestMethod.GET)
    public List<PostTopic> getAll() {
        return postTopicService.getAll();
    }

    @RequestMapping(path = "/post-topics/{topic_id}")
    public PostTopic getOne(@PathVariable("topic_id") Integer id) {
        return postTopicService.getOne(id);
    }

    @RequestMapping(path = "/post-topics/{post_id}",
            method = RequestMethod.POST)
    public void add(@RequestBody PostTopic topic, @PathVariable("post_id") int postId) {
        postTopicService.add(topic, postId);
    }

    @RequestMapping(path = "/post-topics/{topic_id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("topic_id") Integer id) { postTopicService.delete(id);}
}
