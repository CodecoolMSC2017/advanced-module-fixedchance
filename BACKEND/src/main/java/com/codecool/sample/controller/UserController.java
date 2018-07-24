package com.codecool.sample.controller;

import com.codecool.sample.domain.Company;
import com.codecool.sample.domain.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AbstractController {

    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public User loginUser(@RequestBody User user) {
        return userService.find(user.getEmail());
    }

    @RequestMapping(path = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void registerUser(@RequestBody User user) {
        userService.add(user);
    }

    @RequestMapping(path = "/user/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        userService.update(id, user);
    }

    @RequestMapping(path = "/user/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @RequestMapping(path = "/company-login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Company loginCompany(@RequestBody Company company) {
        return companyService.find(company.getEmail());
    }

    @RequestMapping(path = "/company-register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void registerCompany(@RequestBody Company company) {
        companyService.add(company);
    }

    @RequestMapping(path = "/company/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void updateCompany(@PathVariable("id") Integer id, @RequestBody Company company) {
        companyService.update(id, company);
    }

    @RequestMapping(path = "/company/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void deleteCompany(@PathVariable("id") Integer id) {
        companyService.delete(id);
    }
}