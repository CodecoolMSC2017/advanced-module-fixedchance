package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Company;
import com.codecool.fixedchance.domain.SimpleUser;
import com.codecool.fixedchance.domain.User;
import com.codecool.fixedchance.domain.UserDTO;
import com.codecool.fixedchance.exception.MissingUserRoleException;
import com.codecool.fixedchance.exception.UserAlreadyExistsException;
import com.codecool.fixedchance.exception.WrongRoleSelectionException;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class UserController extends AbstractController {

    private final static Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(path = "/login/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User loginUser(@PathVariable("id") Integer id) {
        logger.info(userService.getOne(id) + " logged in.");
        return userService.getOne(id);
    }

    @RequestMapping(path = "/all-users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDTO> getUserDTOs() {
        List<SimpleUser> users = simpleUserService.getAll();
        List<UserDTO> userDtoList = new ArrayList<>();
        for (SimpleUser user : users) {
            UserDTO usdto = new UserDTO();
            usdto.setId(user.getUser().getId());
            usdto.setUserName(user.getUser().getUsername());
            usdto.setExperience(user.getExperience());
            usdto.setRegistrationDate(user.getRegistrationDate());
            usdto.setRole(user.getUser().getAuthorities().get(0));
            userDtoList.add(usdto);
        }
        return userDtoList;
    }

    @RequestMapping(path = "/users/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO getUserDTO(@PathVariable("username") String userName) {
        User user = userService.getUserByName(userName);
        SimpleUser simpleUser = simpleUserService.getByUserId(user.getId());
        UserDTO usdto = new UserDTO();
        usdto.setId(user.getId());
        usdto.setUserName(user.getUsername());
        usdto.setRegistrationDate(simpleUser.getRegistrationDate());
        usdto.setExperience(simpleUser.getExperience());
        usdto.setRole(user.getAuthorities().get(0));
        return usdto;
    }

    @RequestMapping(path = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> getUsers() {
        return userService.getAll();
    }

    /* @RequestMapping(path = "/register",
             method = RequestMethod.POST,
             produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
             consumes = {"application/json"})
     public void registerUser(@RequestBody User user) {
         userService.add(user);
     }
     */
    @PostMapping("/register")
    public void add(@RequestBody Map<String, String> map) throws UserAlreadyExistsException, WrongRoleSelectionException {
        String username = map.get("username");
        String email = map.get("email");
        String password = map.get("password");
        String confirmationPassword = map.get("confirmationPassword");
        String firstName = map.get("firstName");
        String lastName = map.get("lastName");
        String birthDateStr = map.get("birthDate");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String role = map.get("role");
        Date birthDate = null;
        try {
            birthDate = format.parse(birthDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.fatal("Error has been reached unexpectedly while parsing date");
        }
        try {
            userService.add(username, password, confirmationPassword, role);
        } catch (UserAlreadyExistsException e) {
            logger.info(e.getMessage());
            throw new UserAlreadyExistsException();
        }
        try {
            simpleUserService.add(username, email, firstName, lastName, birthDate);
        } catch (WrongRoleSelectionException e) {
            logger.info(e.getMessage());
            throw new WrongRoleSelectionException();
        }
    }

    @RequestMapping(path = "/user/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        userService.update(id, user);
        logger.info(userService.getOne(id) + " updated.");
    }

    @RequestMapping(path = "/user/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        logger.info(userService.getOne(id) + " deleted.");
    }

    @RequestMapping(path = "/company-login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public Company loginCompany(@RequestBody Company company) {
        logger.info(companyService.find(company.getEmail()) + " logged in.");
        return companyService.find(company.getEmail());
    }

    @PostMapping("/company-register")
    public void addCompany(@RequestBody Map<String, String> map) throws UserAlreadyExistsException, WrongRoleSelectionException {
        String username = map.get("username");
        String name = map.get("name");
        String email = map.get("email");
        String password = map.get("password");
        String confirmationPassword = map.get("confirmationPassword");
        String role = map.get("role");
        String subscription = map.get("subscription");
        try {
            userService.add(username, password, confirmationPassword, role);
        } catch (UserAlreadyExistsException e) {
            logger.info(e.getMessage());
            throw new UserAlreadyExistsException();
        }
        try {
            companyService.add(username, name, email, subscription);
        } catch (WrongRoleSelectionException e) {
            logger.info((e.getMessage()));
            throw new WrongRoleSelectionException();
        }
    }

    @RequestMapping(path = "/company/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void updateCompany(@PathVariable("id") Integer id, @RequestBody Company company) {
        companyService.update(id, company);
        logger.info(companyService.getOne(id) + " updated.");
    }

    @RequestMapping(path = "/company/{id}",
            method = RequestMethod.DELETE,
            consumes = {"application/json"})
    public void deleteCompany(@PathVariable("id") Integer id) {
        companyService.delete(id);
        logger.info(companyService.getOne(id) + " deleted.");
    }

    // Google login
    @RequestMapping(path = "/google-login",
            method = RequestMethod.POST,
            headers = "Accept=*/*",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json;charset=UTF-8"})
    public @ResponseBody
    User googleLogin(@RequestBody() String[] params) throws MissingUserRoleException, WrongRoleSelectionException {
        final String token = params[0];
        final String role = params[1];
        if (role != null) {
            return userService.getUserByGoogleToken(token, role);
        } else {
            logger.warn("Improper request arrived. User 'role' was null");
            throw new MissingUserRoleException("Improper request arrived. User 'role' was null");
        }
    }
}