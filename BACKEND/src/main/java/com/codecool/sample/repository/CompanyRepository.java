package com.codecool.sample.repository;

import com.codecool.sample.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    Company findByEmail(String email);
}