package com.codecool.sample.controller;

import com.codecool.sample.model.Company;
import com.codecool.sample.model.User;
import com.codecool.sample.repository.UserRepository;
import com.codecool.sample.service.CompanyService;
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
    @Autowired
    private CompanyService companyService;

    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public User loginUser(@RequestBody User user) throws SQLException, IOException {
        return userService.findByEmail(user.getEmail());
    }

    @RequestMapping(path = "/company-login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Company loginCompany(@RequestBody Company company) throws SQLException, IOException {
        return companyService.findByEmail(company.getEmail());
    }
}