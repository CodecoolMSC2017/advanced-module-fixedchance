package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Integer> {

    List<UserReview> findAll();

    UserReview findByReviewerId(Integer reviewerId);

    UserReview findByReviewedId(Integer reviewedId);
}
