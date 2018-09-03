package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @RequestMapping("/courses")
    public List<Course> getAll() {
        logger.info("returning all courses");
        return courseService.getAll();
    }

    @RequestMapping("/courses/{id}")
    public Course getOne(@PathVariable("id") Integer id) {
        logger.info("returning course with id {}", id);
        return courseService.getOne(id);
    }

    @RequestMapping(path = "/courses/{teacher_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Integer add(@PathVariable("teacher_id") Integer teacherId, @RequestBody Course course) {
        logger.info("adding course to teacher with id {}, {}", teacherId, course);
        courseService.add(teacherId, course);
        return course.getId();
    }

    @RequestMapping(path = "/courses/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody Course course) {
        logger.info("updating course with id {} to {}", id, course);
        courseService.update(id, course);
    }

    @RequestMapping(path = "/courses/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting course with id {}", id);
        courseService.delete(id);
    }

    @RequestMapping(path = "/courses/{teacher_id}/courses",
            method = RequestMethod.GET)
    public List<Course> findByTeacher(@PathVariable("teacher_id") Integer teacherId) {
        logger.info("returning every course of teacher with id{}", teacherId);
        return courseService.findByTeacher(teacherId);
    }

    @RequestMapping(path = "/courses/student/{student_id}",
        method = RequestMethod.GET)
    public List<Course> findByStudentId(@PathVariable("student_id") Integer studentId) {
        logger.info("returning courses of student with id{}", studentId);
        User student = userService.getOne(studentId);
        return courseService.findByStudent(student);
    }
}
