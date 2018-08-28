package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Company;
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
public class CompanyService extends AbstractService {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpleUserService simpleUserService;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getOne(Integer id) {
        return companyRepository.getOne(id);
    }

    public Company find(String email) {
        return companyRepository.findByEmail(email);
    }

    public Company getByUserId(Integer id) {
        return companyRepository.findByUserId(id);
    }

    boolean isCompanyExists(String username) {
        User user = userRepository.findByUsername(username);
        Company company = getByUserId(user.getId());
        return company != null;
    }

    public void add(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    public Company add(String username, String name, String email, String subscription) throws WrongRoleSelectionException {
        User userWithBasicDetails = userRepository.findByUsername(username);
        Company company = new Company();
        company.setUser(userWithBasicDetails);
        company.setName(name);
        company.setEmail(email);
        company.setSubscription(subscription);
        company.setRegistrationDate(new Date());
        company.setActive(true);
        if (!isCompanyExists(username) && !simpleUserService.isSimpleUserExists(username)) {
            companyRepository.save(company);
            return company;
        } else if (!isCompanyExists(username) && simpleUserService.isSimpleUserExists(username)) {
            throw new WrongRoleSelectionException("Wrong role selection");
        } else {
            return companyRepository.findByUserId(userWithBasicDetails.getId());
        }
    }

    public void update(Integer id, Company comp) {
        Company company = companyRepository.getOne(id);
        company.setAll(comp);
        companyRepository.save(company);
    }

    public void delete(Integer id) {
        companyRepository.delete(getOne(id));
    }
}