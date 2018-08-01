package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Company;
import com.codecool.fixedchance.domain.User;
import com.codecool.fixedchance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User get(Principal principal) {
        return userService.getUserByName(principal.getName());
    }

    @GetMapping("/company")
    public Company getCompany(Principal principal) {
        return companyService.getCompanyByName(principal.getName());
    }

    @DeleteMapping("")
    public void delete(HttpSession session) {
        session.invalidate();
    }

    @DeleteMapping("/company")
    public void deleteCompany(HttpSession session) {
        session.invalidate();
    }
}