package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.PostAnswer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostAnswerController extends AbstractController {

    @RequestMapping(path = "/post-answers",
            method = RequestMethod.GET)
    public List<PostAnswer> getAll() {
        return postAnswerService.getAll();
    }

    @RequestMapping(path = "/post-answers/{answer_id}")
    public PostAnswer getOne(@PathVariable("answer_id") Integer id) {
        return postAnswerService.getOne(id);
    }

    @RequestMapping(path = "/post-answers",
            method = RequestMethod.POST)
    public void add(@RequestBody PostAnswer answer) {
        postAnswerService.add(answer);
    }

    @RequestMapping(path = "/post-answers/{answer_id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("answer_id") Integer id) { postAnswerService.delete(id);}
}
