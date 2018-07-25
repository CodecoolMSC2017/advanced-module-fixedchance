package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UserService extends AbstractService {

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(Integer id) {
        return userRepository.getOne(id);
    }

    public User find(String email) {
        return userRepository.findByEmail(email);
    }

    public void add(User user) {
            userRepository.save(user);
    }

    public void update(Integer id, User usr) {
        User user = userRepository.getOne(id);
        user.setAll(usr);
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}