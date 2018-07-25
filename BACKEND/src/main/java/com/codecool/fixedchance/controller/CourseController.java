package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Course;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController extends AbstractController {

    @RequestMapping("/courses")
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @RequestMapping("/courses/{id}")
    public Course getOne(@PathVariable("id") Integer id) {
        return courseService.getOne(id);
    }

    @RequestMapping(path = "/courses/{teacher_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("teacher_id") Integer teacherId, @RequestBody Course course) {
        System.out.println(teacherId + ", " + course);
        courseService.add(teacherId, course);
    }

    @RequestMapping(path = "/courses/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody Course course) {
        courseService.update(id, course);
    }

    @RequestMapping(path = "/courses/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        courseService.delete(id);
    }

    @RequestMapping(path = "/courses/{teacher_id}/courses",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<Course> findByTeacher(@PathVariable("teacher_id") Integer teacherId) {
        return courseService.findByTeacher(teacherId);
    }
}
