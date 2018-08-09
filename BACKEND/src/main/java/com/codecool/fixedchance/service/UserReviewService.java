package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.UserReview;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserReviewService extends AbstractService {

    public UserReview getOne(Integer id) {
        return userReviewRepository.getOne(id);
    }

    public List<UserReview> getAll() {
        return userReviewRepository.findAll();
    }

    public UserReview getByReviewerId(Integer id) {
        return userReviewRepository.findByReviewerId(id);
    }

    public UserReview getByReviewedId(Integer id) {
        return userReviewRepository.findByReviewedId(id);
    }

    public void add(UserReview review) {
        userReviewRepository.save(review);
    }

    public void delete(Integer id) {
        userReviewRepository.deleteById(id);
    }
}
