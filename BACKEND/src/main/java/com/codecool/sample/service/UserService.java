package com.codecool.sample.service;

import com.codecool.sample.domain.User;
import com.codecool.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public final class UserService {

    @Autowired
    private UserRepository repository;

    public Iterable<User> getUsers() {
        return repository.findAll();
    }

    public void addNewUser(User user) {
            repository.save(user);
    }

    public void deleteUser(int userId) {
        repository.deleteById(userId);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}