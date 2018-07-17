package com.codecool.sample.controller;

import com.codecool.sample.domain.Advertisement;
import com.codecool.sample.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class AdvertisementController {

    private AdvertisementService adService;

    @Autowired
    public void setAdService(AdvertisementService adService) {
        this.adService = adService;
    }

    // Get all advertisements
    @RequestMapping("/advertisements")
    public List<Advertisement> getAllAds() throws SQLException {
        return adService.getAdvertisements();
    }

    // Get advertisement by its ID
    @RequestMapping("/advertisements/id")
    public Optional<Advertisement> getAdById(Integer id) throws SQLException {
        return adService.getAdvertisementById(id);
    }

    // Add new advertisement to database
    @RequestMapping(path = "/advertisements/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@RequestBody Advertisement ad) {
        try {
            adService.addNewAdvertisement(ad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
