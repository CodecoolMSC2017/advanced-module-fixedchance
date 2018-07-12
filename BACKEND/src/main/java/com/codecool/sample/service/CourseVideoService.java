package com.codecool.sample.service;

import com.codecool.sample.model.CourseQuestion;
import com.codecool.sample.model.CourseVideo;
import com.codecool.sample.repository.CourseVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CourseVideoService {

    @Autowired
    private CourseVideoRepository videoRepository;

    public List<CourseVideo> getVideos() {
        return videoRepository.findAll();
    }

    public Optional<CourseVideo> getVideoById(Integer id) {
        return videoRepository.findById(id);
    }

    public void addNewVideo(CourseVideo video) throws SQLException {
        try {
            videoRepository.save(video);
        } catch (Exception e) {
            throw new SQLException("Video already exists");
        }
    }
}
