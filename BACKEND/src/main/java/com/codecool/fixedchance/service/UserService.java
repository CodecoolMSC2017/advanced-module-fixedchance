package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
public  class UserService extends AbstractService {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(Integer id) {
        return userRepository.getOne(id);
    }

    public User find(String email) {
        return userRepository.findByEmail(email);
    }

    public void add(User user) {
            userRepository.save(user);
    }

    @Transactional
    public void add(String username, String email, String firstName, String lastName, String password,
                    String confirmationPassword, String role, Date birthDate) {
        if (!password.equals(confirmationPassword)) {
            throw new IllegalArgumentException();
        }
        userDetailsManager.createUser(new org.springframework.security.core.userdetails.User(
                username,
                passwordEncoder.encode(password),
                AuthorityUtils.createAuthorityList("ROLE_" + role)));
        User user = userRepository.findByUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName((lastName));
        user.setBirthDate(birthDate);
        user.setRegistrationDate(new Date());
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void update(Integer id, User usr) {
        User user = userRepository.getOne(id);
        user.setAll(usr);
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}