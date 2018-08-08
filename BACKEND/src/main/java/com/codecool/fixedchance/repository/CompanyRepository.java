package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByEmail(String email);

    Optional<Company> findById(Integer id);

    Company findByUserId(Integer id);
}