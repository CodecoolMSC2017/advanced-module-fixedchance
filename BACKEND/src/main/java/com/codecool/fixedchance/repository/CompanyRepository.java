package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByEmail(String email);
}