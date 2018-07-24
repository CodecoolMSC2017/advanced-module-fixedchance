package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseVideo;
import com.codecool.sample.repository.CourseRepository;
import com.codecool.sample.repository.CourseVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public final class CourseVideoService extends AbstractService {

    public List<CourseVideo> getVideos() {
        return videoRepository.findAll();
    }

    public CourseVideo getVideoById(Integer id) {
        return videoRepository.getOne(id);
    }

    public void addNewVideo(Integer courseId, CourseVideo video) {
        Course course = courseRepository.getOne(courseId);
        video.setCourse(course);
        videoRepository.save(video);
    }
}
