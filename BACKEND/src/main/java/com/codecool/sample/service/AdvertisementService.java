package com.codecool.sample.service;

import com.codecool.sample.domain.Advertisement;
import com.codecool.sample.domain.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class AdvertisementService extends AbstractService {

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Advertisement getOne(Integer id) {
        return advertisementRepository.getOne(id);
    }

    public void add(Integer companyId, Advertisement ad) {
        Company company = companyRepository.getOne(companyId);
        ad.setCompany(company);
        advertisementRepository.save(ad);
    }

    public void update(Integer id, Advertisement ad) {
        Advertisement advertisement = advertisementRepository.getOne(id);
        advertisement.setAll(ad);
        advertisementRepository.save(advertisement);
    }
}
