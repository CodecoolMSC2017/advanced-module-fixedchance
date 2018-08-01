package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
            method = RequestMethod.GET)
    public List<Course> findByTeacher(@PathVariable("teacher_id") Integer teacherId) {
        return courseService.findByTeacher(teacherId);
    }

    @RequestMapping(path = "/courses/student/{student_id}",
        method = RequestMethod.GET)
    public List<Course> findByStudentId(@PathVariable("student_id") Integer studentId) {
        User student = userService.getOne(studentId);
        return courseService.findByStudent(student);
    }
}
