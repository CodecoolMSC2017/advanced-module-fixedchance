package com.codecool.sample.service;

import com.codecool.sample.domain.Company;
import org.springframework.stereotype.Component;

@Component
public final class CompanyService extends AbstractService {

    public Iterable<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company find(String email) {
        return companyRepository.findByEmail(email);
    }

    public void add(Company company) {
        companyRepository.save(company);
    }

    public void delete(int companyId) {
        companyRepository.deleteById(companyId);
    }

    public void update(Integer id, Company comp) {
        Company company = companyRepository.getOne(id);
        company.setAll(comp);
        companyRepository.save(company);
    }
}