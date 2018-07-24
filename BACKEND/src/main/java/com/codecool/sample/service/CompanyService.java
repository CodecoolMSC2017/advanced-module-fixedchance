package com.codecool.sample.service;

import com.codecool.sample.domain.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CompanyService extends AbstractService {

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getOne(Integer id) {
        return companyRepository.getOne(id);
    }

    public Company find(String email) {
        return companyRepository.findByEmail(email);
    }

    public void add(Company company) {
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