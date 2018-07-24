package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseTopic;
import com.codecool.sample.repository.CourseRepository;
import com.codecool.sample.repository.CourseTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public final class CourseTopicService extends AbstractService {

    public List<CourseTopic> getTopics() {
        return topicRepository.findAll();
    }

    public CourseTopic getTopicById(Integer id) {
        return topicRepository.getOne(id);
    }

    public void addNewTopic(Integer courseId, CourseTopic topic) {
        Course course = courseRepository.getOne(courseId);
        topic.setCourse(course);
        topicRepository.save(topic);
    }

}
