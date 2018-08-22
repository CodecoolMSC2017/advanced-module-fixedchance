package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Company;
import com.codecool.fixedchance.domain.SimpleUser;
import com.codecool.fixedchance.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController extends AbstractController {

    @GetMapping("")
    public SimpleUser get(Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return simpleUserService.getByUserId(user.getId());
    }

    @GetMapping("/company")
    public Company getCompany(Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return companyService.getByUserId(user.getId());
    }

    @DeleteMapping("")
    public void delete(HttpSession session) {
        session.invalidate();
    }

}