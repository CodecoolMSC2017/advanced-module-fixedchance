package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Schedule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController extends AbstractController {

    @RequestMapping("/schedules")
    public List<Schedule> getAll() {
        return scheduleService.getAll();
    }

    @RequestMapping("/schedules/{id}")
    public Schedule getOne(@PathVariable("id") Integer id) {
        return scheduleService.getOne(id);
    }

    @RequestMapping(path = "/schedules/{teacher_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("teacher_id") Integer teacherId, @RequestBody Schedule schedule) {
        System.out.println(schedule.toString());
        scheduleService.add(teacherId, schedule);
    }

    @RequestMapping(path = "/schedules/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody Schedule schedule) {
        scheduleService.update(id, schedule);
    }

    @RequestMapping(path = "/schedules/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        scheduleService.delete(id);
    }
}
