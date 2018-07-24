package com.codecool.sample.service;

import com.codecool.sample.domain.Course;
import com.codecool.sample.domain.CourseVideo;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void update(Integer id, CourseVideo video) {
        CourseVideo video1 = videoRepository.getOne(id);
        video1.setAll(video);
        videoRepository.save(video1);
    }

}
