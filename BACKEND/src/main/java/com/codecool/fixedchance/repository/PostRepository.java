package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Post;
import com.codecool.fixedchance.domain.PostTopic;
import com.codecool.fixedchance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByOrderByIdAsc();

    List<Post> findAllByTopics(PostTopic topic);

}
