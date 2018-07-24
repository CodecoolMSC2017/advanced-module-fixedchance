package com.codecool.sample.controller;

import com.codecool.sample.domain.Schedule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController extends AbstractController {

    @RequestMapping("/schedules")
    public List<Schedule> getAll() {
        return scheduleService.getAllSchedules();
    }

    @RequestMapping("/schedules/{id}")
    public Schedule getOne(@PathVariable("id") Integer id) {
        return scheduleService.getScheduleById(id);
    }

    @RequestMapping(path = "/schedules/{teacher_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("teacher_id") Integer teacherId, @RequestBody Schedule schedule) {
        System.out.println(schedule.toString());
        scheduleService.addNewSchedule(teacherId, schedule);
    }

    @RequestMapping(path = "/schedules/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody Schedule schedule) {
        scheduleService.update(id, schedule);
    }
}
