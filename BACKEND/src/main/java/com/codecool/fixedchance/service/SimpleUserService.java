package com.codecool.fixedchance.service;


import com.codecool.fixedchance.domain.SimpleUser;
import com.codecool.fixedchance.domain.User;
import com.codecool.fixedchance.exception.WrongRoleSelectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
public class SimpleUserService extends AbstractService {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyService companyService;

    public List<SimpleUser> getAll() {
        return simpleUserRepository.findAll();
    }

    public SimpleUser getOne(Integer id) {
        return simpleUserRepository.getOne(id);
    }

    public SimpleUser find(String email) {
        return simpleUserRepository.findByEmail(email);
    }

    public SimpleUser getByUserId(Integer id) {
        return simpleUserRepository.findByUserId(id);
    }

    public void add(SimpleUser user) {
        simpleUserRepository.save(user);
    }

    boolean isSimpleUserExists(String username) {
        User user = userRepository.findByUsername(username);
        SimpleUser simpleUser = getByUserId(user.getId());
        return simpleUser != null;
    }

    @Transactional
    public SimpleUser add(String username, String email, String firstName, String lastName, Date birthDate) throws WrongRoleSelectionException {
        SimpleUser user = new SimpleUser();
        User userWithBasicDetails = userRepository.findByUsername(username);
        user.setUser(userWithBasicDetails);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName((lastName));
        user.setBirthDate(birthDate);
        user.setRegistrationDate(new Date());
        if (!isSimpleUserExists(username) && !companyService.isCompanyExists(username)) {
            simpleUserRepository.save(user);
            return user;
        } else if (!isSimpleUserExists(username) && companyService.isCompanyExists(username)) {
            throw new WrongRoleSelectionException("Wrong role selection.");
        } else {
            return simpleUserRepository.findByUserId(userWithBasicDetails.getId());
        }
    }
}

   /* public void update(Integer id, SimpleUser usr) {
        SimpleUser user = simpleUserRepository.getOne(id);
        user.setAll(usr);
        simpleUserRepository.save(user);
    }


    public void delete(Integer id) {
        simpleUserRepository.deleteById(id);
    }

}
*/
