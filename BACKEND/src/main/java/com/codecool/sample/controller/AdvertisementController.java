package com.codecool.sample.controller;

import com.codecool.sample.domain.Advertisement;
import com.codecool.sample.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class AdvertisementController extends AbstractController {

    @RequestMapping("/advertisements")
    public List<Advertisement> getAllAds() throws SQLException {
        return advertisementService.getAdvertisements();
    }

    @RequestMapping("/advertisements/{id}")
    public Advertisement getAdById(@PathVariable("id") Integer id) throws SQLException {
        return advertisementService.getAdvertisementById(id);
    }

    @RequestMapping(path = "/advertisements/{company_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = {"application/json"})
    public void add(@PathVariable("company_id") Integer companyId, @RequestBody Advertisement ad) {
        advertisementService.addNewAdvertisement(companyId, ad);
    }
}
