package com.codecool.sample.controller;

import com.codecool.sample.domain.Company;
import com.codecool.sample.domain.User;
import com.codecool.sample.service.CompanyService;
import com.codecool.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class LoginController extends AbstractController {

    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public User loginUser(@RequestBody User user) {
        return userService.findByEmail(user.getEmail());
    }

    @RequestMapping(path = "/company-login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Company loginCompany(@RequestBody Company company) {
        return companyService.findByEmail(company.getEmail());
    }
}