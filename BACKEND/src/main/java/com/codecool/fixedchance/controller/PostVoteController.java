package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.PostVote;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostVoteController extends AbstractController{

    @RequestMapping(path="/votes",
            method = RequestMethod.GET)
    public List<PostVote> getAll() {return postVoteService.findAll();}

    @RequestMapping(path = "/vote",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody PostVote postVote) {
        postVoteService.add(postVote);}
}
