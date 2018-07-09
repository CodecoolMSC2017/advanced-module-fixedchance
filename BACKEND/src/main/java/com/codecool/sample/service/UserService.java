package com.codecool.sample.service;

import com.codecool.sample.model.User;
import com.codecool.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public final class UserService {

    @Autowired
    private UserRepository repository;

    public Iterable<User> getUsers() throws SQLException {
        return repository.findAll();
    }

    public void addNewUser(User user) throws SQLException {
        try {
            repository.save(user);
        } catch (Exception e) {
            throw new SQLException("E-mail already exists");
        }
    }

    public void deleteUser(int userId) throws SQLException {
        repository.deleteById(userId);
    }

    public User findByEmail(String email) throws SQLException {
        return repository.findByEmail(email);
    }
}