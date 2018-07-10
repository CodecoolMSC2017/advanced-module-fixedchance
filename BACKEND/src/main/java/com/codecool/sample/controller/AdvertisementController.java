package com.codecool.sample.controller;

import com.codecool.sample.model.Advertisement;
import com.codecool.sample.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementService adService;

    @RequestMapping("/advertisements")
    public List<Advertisement> getAllAds() throws SQLException {
        return adService.getAdvertisements();
    }

    @RequestMapping("/advertisements/id")
    public Optional<Advertisement> getAdById(Integer id) {
        return adService.getAdvertisementById(id);
    }

    @RequestMapping("/advertisements/add")
    public void add(Advertisement ad) {
        try {
            adService.addNewAdvertisement(ad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
