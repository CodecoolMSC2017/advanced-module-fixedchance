package com.codecool.sample.controller;

import com.codecool.sample.model.User;
import com.codecool.sample.repository.UserRepository;
import com.codecool.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public User loginUser(@RequestBody User user) throws SQLException, IOException {
        return userService.findByEmail(user.getEmail());
    }
}