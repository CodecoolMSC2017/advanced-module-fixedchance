package com.codecool.sample.service;

import com.codecool.sample.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

class AbstractService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseQuestionRepository questionRepository;

    @Autowired
    CourseAnswerRepository answerRepository;

    @Autowired
    CourseReviewRepository reviewRepository;

    @Autowired
    CourseTopicRepository topicRepository;

    @Autowired
    CourseVideoRepository videoRepository;

    @Autowired
    ScheduleRepository scheduleRepository;
}
