package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

    List<PostComment> findAll();
}
