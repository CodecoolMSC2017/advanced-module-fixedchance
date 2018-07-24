package com.codecool.sample.controller;

import com.codecool.sample.domain.Company;
import com.codecool.sample.domain.CourseVideo;
import com.codecool.sample.domain.Schedule;
import com.codecool.sample.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Get all schedules
    @RequestMapping("/schedules")
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // Get schedule by its ID
    @RequestMapping("/schedules/{id}")
    public Schedule getScheduleById(@PathVariable("id") Integer id) {
        return scheduleService.getScheduleById(id);
    }

    @RequestMapping(path = "/schedules/{teacher_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void addSchedule(@PathVariable("teacher_id") Integer teacherId, @RequestBody Schedule schedule) {
        System.out.println(schedule.toString());
        scheduleService.addNewSchedule(teacherId, schedule);
    }
}
