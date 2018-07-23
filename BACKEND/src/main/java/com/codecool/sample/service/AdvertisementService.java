package com.codecool.sample.service;

import com.codecool.sample.domain.Advertisement;
import com.codecool.sample.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public final class AdvertisementService {

    @Autowired
    private AdvertisementRepository repository;

    public List<Advertisement> getAdvertisements() {
        return repository.findAll();
    }

    public void addNewAdvertisement(Advertisement ad) {
        repository.save(ad);
    }

    public Optional<Advertisement> getAdvertisementById(Integer id) {
        return repository.findById(id);
    }
}
