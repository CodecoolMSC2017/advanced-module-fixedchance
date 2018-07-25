package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Schedule;
import com.codecool.fixedchance.domain.User;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public final class ScheduleService extends AbstractService {

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public Schedule getOne(Integer id) {
        return scheduleRepository.getOne(id);
    }

    public void add(Integer teacherId, Schedule schedule) {
        User teacher = userRepository.getOne(teacherId);
        schedule.setTeacher(teacher);
        System.out.println(schedule.toString());
        scheduleRepository.save(schedule);
    }

    public void update(Integer id, Schedule schedule) {
        Schedule schedule1 = scheduleRepository.getOne(id);
        schedule1.setAll(schedule);
        scheduleRepository.save(schedule1);
    }

    public void delete(Integer id) {
        scheduleRepository.deleteById(id);
    }
}
