package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Company;
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

    public Company getCompanyByName(String username) {
        return companyRepository.findByUsername(username);
    }

    public void add(Company company) {
        companyRepository.save(company);
    }

    // TODO: find a better solution
    @Transactional
    public void add(String email, String username, String name, String password,
                    String confirmationPassword, String role, String subscription) {
        if (!password.equals(confirmationPassword)) {
            throw new IllegalArgumentException();
        }

        userDetailsManager.createUser(new org.springframework.security.core.userdetails.User(
                username,
                passwordEncoder.encode(password),
                AuthorityUtils.createAuthorityList("ROLE_" + role)));

        Company company = new Company();
        company.setUsername(username);
        company.setPassword(passwordEncoder.encode(password));
        company.setEmail(email);
        company.setName(name);
        company.setRegistrationDate(new Date());
        company.setSubscription(subscription);
        company.setActive(true);
        company.setAuthorities(List.of("ROLE_" + role));
        company.setEnabled(true);
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