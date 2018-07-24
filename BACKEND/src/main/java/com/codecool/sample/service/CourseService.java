package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseService extends AbstractService {

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getOne(int id) { return courseRepository.getOne(id); }

    public void add(Integer teacherId, Course course) {
        User teacher = userRepository.getOne(teacherId);
        course.setTeacher(teacher);
        System.out.println(course);
        courseRepository.save(course);
    }

    public void update(Integer id, Course course) {
        Course course1 = courseRepository.getOne(id);
        course1.setName(course.getName());
        courseRepository.save(course1);
    }

    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }

}
