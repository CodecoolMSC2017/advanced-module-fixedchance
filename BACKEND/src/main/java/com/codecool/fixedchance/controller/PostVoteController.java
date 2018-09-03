package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.PostVote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostVoteController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(PostVoteController.class);

    @RequestMapping(path="/votes",
            method = RequestMethod.GET)
    public List<PostVote> getAll() {
        logger.info("returning all votes");
        return postVoteService.findAll();
    }

    @RequestMapping(path = "/vote",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody PostVote postVote) {
        logger.info("adding post-vote {}", postVote);
        postVoteService.add(postVote);}
}
