package com.codecool.sample.service;

import com.codecool.sample.domain.CourseVideo;
import com.codecool.sample.domain.Schedule;
import com.codecool.sample.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }

    public Optional<Schedule> getScheduleById(Integer id) {
        return repository.findById(id);
    }

    public void addNewSchedule(Schedule schedule) {
        repository.save(schedule);
    }
}
