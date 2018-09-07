package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Advertisement;
import com.codecool.fixedchance.domain.Company;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class AdvertisementService extends AbstractService {

    private final static Logger logger = Logger.getLogger(AdvertisementService.class);

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Advertisement getOne(Integer id) {
        return advertisementRepository.getOne(id);
    }

    public Advertisement add(Integer companyId, Advertisement ad) {
        Company company = companyRepository.findById(companyId).orElse(null);
        ad.setCompany(company);
        advertisementRepository.save(ad);
        logger.info(ad + " added to database.");
        return ad;
    }

    public void update(Integer id, Advertisement ad) {
        Advertisement advertisement = advertisementRepository.getOne(id);
        advertisement.setAll(ad);
        advertisementRepository.save(advertisement);
        logger.info(ad + " updated.");
    }

    public void delete(Integer id) {
        advertisementRepository.deleteById(id);
        logger.info(advertisementRepository.getOne(id) + " deleted.");
    }

    public List<Advertisement> findByCompany(Integer companyId) {
        Company company = companyRepository.getOne(companyId);
        return advertisementRepository.findByCompany(company);
    }
}
