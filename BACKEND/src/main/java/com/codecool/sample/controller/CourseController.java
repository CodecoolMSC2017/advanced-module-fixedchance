package com.codecool.sample.controller;

import com.codecool.sample.domain.Course;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController extends AbstractController {

    @RequestMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping("/courses/{id}")
    public Course getCourseById(@PathVariable("id") Integer id) {
        return courseService.getById(id);
    }

    @RequestMapping(path = "/courses/{teacher_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("teacher_id") Integer teacherId, @RequestBody Course course) {
        System.out.println(teacherId + ", " + course);
        courseService.addNewCourse(teacherId, course);
    }
}
