package com.codecool.fixedchance.service;

import com.codecool.fixedchance.repository.*;
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

    @Autowired
    CourseStudentRepository courseStudentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    SimpleUserRepository simpleUserRepository;

    @Autowired
    UserReviewRepository userReviewRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    PostTopicRepository postTopicRepository;

    @Autowired
    StudentAnswerRepository studentAnswerRepository;

    @Autowired
    PostVoteRepository postVoteRepository;
}
