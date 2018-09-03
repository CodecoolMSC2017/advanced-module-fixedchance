package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @RequestMapping("/schedules")
    public List<Schedule> getAll() {
        logger.info("returning all schedules");
        return scheduleService.getAll();
    }

    @RequestMapping("/schedules/{id}")
    public Schedule getOne(@PathVariable("id") Integer id) {
        logger.info("returning schedule with id {}", id);
        return scheduleService.getOne(id);
    }

    @RequestMapping(path = "/schedules/{teacher_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("teacher_id") Integer teacherId, @RequestBody Schedule schedule) {
        logger.info("creating schedule for teacher with id {}, {}", teacherId, schedule);
        scheduleService.add(teacherId, schedule);
    }

    @RequestMapping(path = "/schedules/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody Schedule schedule) {
        logger.info("updating schedule with id {} to {}", id, schedule);
        scheduleService.update(id, schedule);
    }

    @RequestMapping(path = "/schedules/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting schedule with id {}", id);
        scheduleService.delete(id);
    }

    @RequestMapping(path = "/schedules/{teacher_id}/schedules",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<Schedule> findByTeacher(@PathVariable("teacher_id") Integer teacherId) {
        logger.info("returning schedules of teacher with id {}", teacherId);
        return scheduleService.findByTeacher(teacherId);
    }
}
