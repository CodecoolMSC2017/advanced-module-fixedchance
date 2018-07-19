package com.codecool.sample.repository;

import com.codecool.sample.domain.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseVideoRepository extends JpaRepository<CourseVideo, Integer> {

    List<CourseVideo> findAll();
}
