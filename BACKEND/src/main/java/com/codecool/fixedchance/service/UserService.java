package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.User;
import com.codecool.fixedchance.exception.UserAlreadyExistsException;
import com.codecool.fixedchance.exception.WrongRoleSelectionException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService extends AbstractService {

    private final static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SimpleUserService simpleUserService;

    @Autowired
    private CompanyService companyService;

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(Integer id) {
        return userRepository.getOne(id);
    }

    @Transactional
    public User add(String username, String password, String confirmationPassword, String role) throws UserAlreadyExistsException {
        if (!password.equals(confirmationPassword)) {
            throw new IllegalArgumentException();
        }
        User user;
        if (!isUsernameExists(username)) {
            userDetailsManager.createUser(new org.springframework.security.core.userdetails.User(
                    username,
                    passwordEncoder.encode(password),
                    AuthorityUtils.createAuthorityList("ROLE_" + role)));
            user = userRepository.findByUsername(username);
            user.setEnabled(true);
            user.setAll(user);
            userRepository.save(user);
            logger.info(userRepository.findByUsername(username) + " added to database.");
        } else {
            logger.info("Repeated registration attempt. User already exists.");
            throw new UserAlreadyExistsException("Repeated registration attempt. User already exists.");
        }
        return user;
    }

    @Transactional
    public User add(User user) {
        List<GrantedAuthority> roles = user.getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                null,
                roles);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return user;
    }

    private boolean isUsernameExists(String username) {
        User user = getUserByName(username);
        return user != null;
    }

    private User getGoogleAuthenticatedUser(String username, String role) {
        if (!isUsernameExists(username)) {
            userDetailsManager.createUser(new org.springframework.security.core.userdetails.User(
                    username,
                    "",
                    AuthorityUtils.createAuthorityList("ROLE_" + role)));
            logger.info(getUserByName(username) + " added to database.");
        }
        return getUserByName(username);
    }

    // TODO: Move to a new package as a regular method
    public GoogleIdToken.Payload getGooglePayload(final String googleToken, final String clientId) {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(googleToken);
        } catch (GeneralSecurityException | IOException e) {
            logger.fatal("Unable to read google token.", e);
        }
        if (idToken != null) {
            return idToken.getPayload();
        }
        return null;
    }

    @Transactional
    public User getUserByGoogleToken(String token, String role) throws WrongRoleSelectionException {
        final String CLIENT_ID = "899873551530-nq1cl62rki8ehf4qgc3dm8hnp9l5icvi.apps.googleusercontent.com";
        User user;
        GoogleIdToken.Payload payload = getGooglePayload(token, CLIENT_ID);

        String email = payload.getEmail();
        // creating username from the first part of the email address
        String username = email.split("@")[0];

        String name = (String) payload.get("name");
        String firstName = (String) payload.get("given_name");
        String lastName = (String) payload.get("family_name");
        // Later
        //   String pictureUrl = (String) payload.get("picture");
        //   String locale = (String) payload.get("locale");

        user = getGoogleAuthenticatedUser(username, role);

        add(user);

        String currentRole = user.getAuthorities().get(0).split("_")[1];

        if (currentRole.equals(role)) {
            if (role.equals("STUDENT") || role.equals("TEACHER")) {
                try {
                    simpleUserService.add(username, email, firstName, lastName, null);
                } catch (WrongRoleSelectionException e) {
                    logger.info("Wrong role selection.");
                    throw new WrongRoleSelectionException();
                }
            } else if (role.equals("COMPANY")) {
                try {
                    // The default company name is the username
                    // and the default subscription value is 'later'
                    companyService.add(username, username, email, "later");
                } catch (WrongRoleSelectionException e) {
                    logger.info("Wrong role selection.");
                    throw new WrongRoleSelectionException();
                }
            }
        } else {
            logger.info("Wrong role selection.");
            throw new WrongRoleSelectionException();
        }
        return user;
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