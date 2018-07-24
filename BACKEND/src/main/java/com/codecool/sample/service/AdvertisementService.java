package com.codecool.sample.service;

import com.codecool.sample.domain.Advertisement;
import com.codecool.sample.domain.Company;
import com.codecool.sample.repository.AdvertisementRepository;
import com.codecool.sample.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public final class AdvertisementService extends AbstractService {

    public List<Advertisement> getAdvertisements() {
        return advertisementRepository.findAll();
    }

    public Advertisement getAdvertisementById(Integer id) {
        return advertisementRepository.getOne(id);
    }

    public void addNewAdvertisement(Integer companyId, Advertisement ad) {
        Company company = companyRepository.getOne(companyId);
        ad.setCompany(company);
        advertisementRepository.save(ad);
    }
}
