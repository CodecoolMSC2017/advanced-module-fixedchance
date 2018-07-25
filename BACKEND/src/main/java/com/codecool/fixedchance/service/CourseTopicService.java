package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseTopic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseTopicService extends AbstractService {

    public List<CourseTopic> getAll() {
        return topicRepository.findAll();
    }

    public CourseTopic getOne(Integer id) {
        return topicRepository.getOne(id);
    }

    public void add(Integer courseId, CourseTopic topic) {
        Course course = courseRepository.getOne(courseId);
        topic.setCourse(course);
        topicRepository.save(topic);
    }

    public void update(Integer id, CourseTopic topic) {
        CourseTopic topic1 = topicRepository.getOne(id);
        topic1.setAll(topic);
        topicRepository.save(topic1);
    }

    public void delete(Integer id) {
        topicRepository.deleteById(id);
    }

    public List<CourseTopic> findByCourse(Integer courseId) {
        Course course = courseRepository.getOne(courseId);
        return topicRepository.findByCourse(course);
    }

}
