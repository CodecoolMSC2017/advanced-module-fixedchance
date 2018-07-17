package com.codecool.sample.controller;

import com.codecool.sample.domain.Company;
import com.codecool.sample.domain.Message;
import com.codecool.sample.domain.User;
import com.codecool.sample.service.CompanyService;
import com.codecool.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(path = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Message registerUser(@RequestBody User user) throws SQLException, IOException {
        try {
            userService.addNewUser(user);
            return new Message("Registration successful");
        } catch (SQLException sql) {
            return new Message("E-mail already in use");
        }
    }

    @RequestMapping(path = "/company-register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Message registerCompany(@RequestBody Company company) throws SQLException, IOException {
        System.out.println(company.toString());
        try {
            companyService.addNewCompany(company);
            return new Message("Registration successful");
        } catch (SQLException sql) {
            return new Message("E-mail already in use");
        }
    }
}