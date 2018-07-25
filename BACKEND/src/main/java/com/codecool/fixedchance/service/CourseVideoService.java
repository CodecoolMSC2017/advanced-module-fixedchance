package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Course;
import com.codecool.fixedchance.domain.CourseVideo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseVideoService extends AbstractService {

    public List<CourseVideo> getAll() {
        return videoRepository.findAll();
    }

    public CourseVideo getOne(Integer id) {
        return videoRepository.getOne(id);
    }

    public void add(Integer courseId, CourseVideo video) {
        Course course = courseRepository.getOne(courseId);
        video.setCourse(course);
        videoRepository.save(video);
    }

    public void update(Integer id, CourseVideo video) {
        CourseVideo video1 = videoRepository.getOne(id);
        video1.setAll(video);
        videoRepository.save(video1);
    }

    public void delete(Integer id) {
        videoRepository.deleteById(id);
    }

    public List<CourseVideo> findByCourse(Integer courseId) {
        Course course = courseRepository.getOne(courseId);
        return videoRepository.findByCourse(course);
    }
}
