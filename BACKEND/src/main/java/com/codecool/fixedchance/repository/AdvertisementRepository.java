package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.Advertisement;
import com.codecool.fixedchance.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    List<Advertisement> findAll();

    List<Advertisement> findByCompany(Company company);
}
