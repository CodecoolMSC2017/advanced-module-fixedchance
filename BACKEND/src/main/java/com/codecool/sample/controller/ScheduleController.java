package com.codecool.sample.controller;

import com.codecool.sample.model.Schedule;
import com.codecool.sample.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/schedules")
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }
}
