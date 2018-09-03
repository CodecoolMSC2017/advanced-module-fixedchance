package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.PostTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostTopicController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(PostTopicController.class);

    @RequestMapping(path = "/post-topics",
            method = RequestMethod.GET)
    public List<PostTopic> getAll() {
        logger.info("returning all post-topics");
        return postTopicService.getAll();
    }

    @RequestMapping(path = "/post-topics/{topic_id}")
    public PostTopic getOne(@PathVariable("topic_id") Integer id) {
        logger.info("returning post-topic with id {}", id);
        return postTopicService.getOne(id);
    }

    @RequestMapping(path = "/post-topics/{post_id}",
            method = RequestMethod.POST)
    public void add(@RequestBody PostTopic topic, @PathVariable("post_id") int postId) {
        logger.info("creating post-topic for post with id {}, {}", postId, topic);
        postTopicService.add(topic, postId);
    }

    @RequestMapping(path = "/post-topics/{topic_id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("topic_id") Integer id) {
        logger.info("deleting post-topic with id {}", id);
        postTopicService.delete(id);
    }
}
