package com.codecool.sample.service;

import com.codecool.sample.domain.Company;
import com.codecool.sample.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public final class CompanyService extends AbstractService {

    public Iterable<Company> getUsers() {
        return companyRepository.findAll();
    }

    public Company findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    public void addNewCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(int companyId) {
        companyRepository.deleteById(companyId);
    }
}