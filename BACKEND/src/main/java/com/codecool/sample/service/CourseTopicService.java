package com.codecool.sample.service;

import com.codecool.sample.domain.CourseTopic;
import com.codecool.sample.repository.CourseTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseTopicService {

    @Autowired
    private CourseTopicRepository topicRepository;

    public List<CourseTopic> getTopics() {
        return topicRepository.findAll();
    }

    public Optional<CourseTopic> getTopicById(Integer id) {
        return topicRepository.findById(id);
    }

    public void addNewTopic(CourseTopic topic) {
        topicRepository.save(topic);
    }

}
