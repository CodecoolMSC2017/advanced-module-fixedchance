package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseStudent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseStudentController extends AbstractController {

    @RequestMapping(path = "/course-student",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody CourseStudent courseStudent) {
        courseStudentService.add(courseStudent);
    }
}
