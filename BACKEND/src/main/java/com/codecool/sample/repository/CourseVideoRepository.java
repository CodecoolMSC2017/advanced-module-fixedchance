package com.codecool.sample.repository;

import com.codecool.sample.model.CourseVideo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseVideoRepository extends CrudRepository<CourseVideo, Integer> {

    List<CourseVideo> findAll();
}
