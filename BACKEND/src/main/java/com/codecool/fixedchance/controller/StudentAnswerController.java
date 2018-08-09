package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.StudentAnswer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentAnswerController extends AbstractController {

    @RequestMapping(path = "/student-answers",
            method = RequestMethod.GET)
    public List<StudentAnswer> getAll() {
        return studentAnswerService.findAll();
    }
}
