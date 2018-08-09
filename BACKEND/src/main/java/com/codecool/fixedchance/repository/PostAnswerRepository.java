package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.PostAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostAnswerRepository extends JpaRepository<PostAnswer, Integer> {

    List<PostAnswer> findAll();
}
