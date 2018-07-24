package com.codecool.sample.service;

import com.codecool.sample.domain.User;
import org.springframework.stereotype.Component;

@Component
public final class UserService extends AbstractService {

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User user) {
            userRepository.save(user);
    }

    public void delete(int userId) {
        userRepository.deleteById(userId);
    }

    public User find(String email) {
        return userRepository.findByEmail(email);
    }

    public void update(Integer id, User usr) {
        User user = userRepository.getOne(id);
        user.setAll(usr);
        userRepository.save(user);
    }
}