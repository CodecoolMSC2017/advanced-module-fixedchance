package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Company;
import com.codecool.fixedchance.domain.User;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class UserController extends AbstractController {

    @RequestMapping(path = "/login/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User loginUser(@PathVariable("id") Integer id) {
        return userService.getOne(id);
    }

    @RequestMapping(path = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> getUsers(){ return userService.getAll();}

   /* @RequestMapping(path = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void registerUser(@RequestBody User user) {
        userService.add(user);
    }
    */
   @PostMapping("/register")
   public void add(@RequestBody Map<String, String> map) {
       String username = map.get("username");
       String email = map.get("email");
       String password = map.get("password");
       String confirmationPassword = map.get("confirmationPassword");
       String firstName = map.get("firstName");
       String lastName = map.get("lastName");
       String birthDateStr = map.get("birthDate");
       DateFormat format = new SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH);
       String role = map.get("role");
       Date birthDate = null;
       try {
           birthDate = format.parse(birthDateStr);
       } catch (ParseException e) {
           e.printStackTrace();
       }
       userService.add(username, email, firstName, lastName, password, confirmationPassword, role, birthDate);
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
/*
    @RequestMapping(path = "/company-register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void registerCompany(@RequestBody Company company) {
        companyService.add(company);
    }
*/
@PostMapping("/company-register")
public void addCompany(@RequestBody Map<String, String> map) {
    String username = map.get("username");
    String name = map.get("name");
    String email = map.get("email");
    String password = map.get("password");
    String confirmationPassword = map.get("confirmationPassword");
    String role = map.get("role");
    String subscription = map.get("subscription");
    companyService.add(username, name, email, password, confirmationPassword, role, subscription);
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

    // Google login
    @RequestMapping(path = "/google-login",
            method = RequestMethod.POST,
            headers="Accept=*/*",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/x-www-form-urlencoded"})
    public @ResponseBody User googleLogin(@RequestBody() String token) {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        final String CLIENT_ID = "899873551530-nq1cl62rki8ehf4qgc3dm8hnp9l5icvi.apps.googleusercontent.com";

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(token);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            // TODO: log here
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            String name = (String) payload.get("name");
            // Later
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            // TODO: Remove prints
            System.out.println(email);
            System.out.println(name);
            return new User();
        }
        return null;
    }
}