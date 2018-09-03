package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseAnswerController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CourseAnswerController.class);

    @RequestMapping("/courses/answers")
    public List<CourseAnswer> getAll() {
        logger.info("returning all answers");
        return answerService.getAll();
    }

    @RequestMapping("/courses/answers/{id}")
    public CourseAnswer getOne(@PathVariable("id") Integer id) {
        logger.info("returning answer with id {}", id);
        return answerService.getOne(id);
    }

    @RequestMapping(path = "/courses/{course_id}/questions/{question_id}/answers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("course_id") Integer courseId, @PathVariable("question_id") Integer questionId, @RequestBody CourseAnswer answer) {
        logger.info("adding answer courseId {}, questionId {}, {}", courseId, questionId, answer);
        answerService.add(courseId, questionId, answer);
    }

    @RequestMapping(path = "/courses/answers/{id}",
                   method = RequestMethod.PUT,
                   consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody CourseAnswer answer) {
        logger.info("updating answer with id {} to {}", id, answer);
        answerService.update(id, answer);
    }

    @RequestMapping(path = "/courses/answers/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting answer with id {}", id);
        answerService.delete(id);
    }

    @RequestMapping(path = "/courses/questions/{question_id}",
                    method = RequestMethod.GET,
                    consumes = {"application/json"})
    public List<CourseAnswer> getAnswersForQuestion(@PathVariable("question_id") Integer questionId) {
        logger.info("returning answers for question with id {}", questionId);
        return answerService.findAnswers(questionId);
    }
}
