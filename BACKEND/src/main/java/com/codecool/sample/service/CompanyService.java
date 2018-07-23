package com.codecool.sample.service;

import com.codecool.sample.domain.Company;
import com.codecool.sample.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public final class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public Iterable<Company> getUsers() {
        return repository.findAll();
    }

    public void addNewCompany(Company company) {
        repository.save(company);
    }

    public void deleteCompany(int companyId) {
        repository.deleteById(companyId);
    }

    public Company findByEmail(String email) {
        return repository.findByEmail(email);
    }
}