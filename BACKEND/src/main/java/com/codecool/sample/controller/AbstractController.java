package com.codecool.sample.controller;

import com.codecool.sample.service.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractController {

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseQuestionService questionService;

    @Autowired
    CourseAnswerService answerService;

    @Autowired
    CourseReviewService reviewService;

    @Autowired
    CourseTopicService topicService;

    @Autowired
    CourseVideoService videoService;

    @Autowired
    ScheduleService scheduleService;
}
