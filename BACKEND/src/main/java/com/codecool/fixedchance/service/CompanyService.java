package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Company;
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
public class CompanyService extends AbstractService {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public void add(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    public void add(String username, String name, String email, String subscription) {
        User user = userRepository.findByUsername(username);
        Company company = new Company();
        company.setUser(user);
        company.setName(name);
        company.setEmail(email);
        company.setSubscription(subscription);
        company.setRegistrationDate(new Date());
        company.setActive(true);
        companyRepository.save(company);
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