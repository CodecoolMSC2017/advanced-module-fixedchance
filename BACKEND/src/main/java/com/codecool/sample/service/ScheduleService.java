package com.codecool.sample.service;

import com.codecool.sample.domain.Schedule;
import com.codecool.sample.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }
}
