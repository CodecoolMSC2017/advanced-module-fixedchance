package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Schedule;
import com.codecool.fixedchance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAll();

    List<Schedule> findByTeacher(User user);
}
