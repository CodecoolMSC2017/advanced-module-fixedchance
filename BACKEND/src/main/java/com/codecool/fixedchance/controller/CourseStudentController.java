package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.CourseStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseStudentController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CourseStudentController.class);

    @RequestMapping(path = "/course-student",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseStudent courseStudent) {
        logger.info("creating connection between course with id {} and student with id {}", courseStudent.getCourseId(), courseStudent.getStudentId());
        courseStudentService.add(courseStudent);
    }

}