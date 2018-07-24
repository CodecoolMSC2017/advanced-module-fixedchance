package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseTopic;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void update(Integer id, CourseTopic topic) {
        CourseTopic topic1 = topicRepository.getOne(id);
        topic1.setAll(topic);
        topicRepository.save(topic1);
    }

}
