package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.User;
import com.codecool.sample.repository.CourseRepository;
import com.codecool.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public final class CourseService extends AbstractService {

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getById(int id) { return courseRepository.getOne(id); }

    public void addNewCourse(Integer teacherId, Course course) {
        User teacher = userRepository.getOne(teacherId);
        course.setTeacher(teacher);
        System.out.println(course);
        courseRepository.save(course);
    }
}
