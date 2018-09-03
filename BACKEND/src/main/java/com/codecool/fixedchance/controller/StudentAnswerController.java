package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.StudentAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentAnswerController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(StudentAnswerController.class);

    @RequestMapping(path = "/student-answers",
            method = RequestMethod.GET)
    public List<StudentAnswer> getAll() {
        logger.info("returning all student-answers");
        return studentAnswerService.findAll();
    }

    @RequestMapping("/student-answers/{id}")
    public StudentAnswer getOne(@PathVariable("id") Integer id) {
        logger.info("returning student-answer with id {}", id);
        return studentAnswerService.getOne(id);
    }

    @RequestMapping(path = "/student-answers/{student_answer_id}/answers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("student_answer_id") Integer answerId, @RequestBody StudentAnswer answer) {
        logger.info("creating student-answer for answer with id {}, {}", answerId, answer);
        studentAnswerService.add(answerId, answer);
    }

    @RequestMapping(path = "/student-answers/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting answer with id {}", id);
        studentAnswerService.delete(id);
    }

}
