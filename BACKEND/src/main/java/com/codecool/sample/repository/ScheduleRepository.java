package com.codecool.sample.repository;

import com.codecool.sample.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    List<Schedule> findAll();
}
