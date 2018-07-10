package com.codecool.sample.service;

import com.codecool.sample.model.Company;
import com.codecool.sample.model.User;
import com.codecool.sample.repository.CompanyRepository;
import com.codecool.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public final class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public Iterable<Company> getUsers() throws SQLException {
        return repository.findAll();
    }

    public void addNewCompany(Company company) throws SQLException {
        try {
            repository.save(company);
        } catch (Exception e) {
            throw new SQLException("E-mail already exists");
        }
    }

    public void deleteCompany(int companyId) throws SQLException {
        repository.deleteById(companyId);
    }

    public Company findByEmail(String email) throws SQLException {
        return repository.findByEmail(email);
    }
}