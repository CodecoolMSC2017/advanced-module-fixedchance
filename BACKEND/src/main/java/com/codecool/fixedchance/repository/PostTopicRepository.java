package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.PostTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTopicRepository extends JpaRepository<PostTopic, Integer> {

    List<PostTopic> findAll();
}
