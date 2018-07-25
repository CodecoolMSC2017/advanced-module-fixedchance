package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Advertisement;
import com.codecool.fixedchance.domain.Company;
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

    public void delete(Integer id) {
        advertisementRepository.deleteById(id);
    }

    public List<Advertisement> findByCompany(Integer companyId) {
        Company company = companyRepository.getOne(companyId);
        return advertisementRepository.findByCompany(company);
    }
}