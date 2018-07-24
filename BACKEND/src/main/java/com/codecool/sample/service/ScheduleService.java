package com.codecool.sample.service;

import com.codecool.sample.domain.CourseVideo;
import com.codecool.sample.domain.Schedule;
import com.codecool.sample.domain.User;
import com.codecool.sample.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public final class ScheduleService extends AbstractService {

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Integer id) {
        return scheduleRepository.getOne(id);
    }

    public void addNewSchedule(Integer teacherId, Schedule schedule) {
        User teacher = userRepository.getOne(teacherId);
        schedule.setTeacher(teacher);
        System.out.println(schedule.toString());
        scheduleRepository.save(schedule);
    }
}
