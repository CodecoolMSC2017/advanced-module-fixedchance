package com.codecool.sample.controller;

import com.codecool.sample.domain.Course;
import com.codecool.sample.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping("/courses/{id}")
    public Course getCourseById(@PathVariable("id") Integer id) {
        return courseService.getById(id);
    }

    @RequestMapping(path = "/courses",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody Course course) {
        courseService.addNewCourse(course);
    }
}
