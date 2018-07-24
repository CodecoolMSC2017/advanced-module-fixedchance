package com.codecool.sample.controller;

import com.codecool.sample.domain.Advertisement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertisementController extends AbstractController {

    @RequestMapping("/advertisements")
    public List<Advertisement> getAll() {
        return advertisementService.getAll();
    }

    @RequestMapping("/advertisements/{id}")
    public Advertisement getOne(@PathVariable("id") Integer id) {
        return advertisementService.getOne(id);
    }

    @RequestMapping(path = "/advertisements/{company_id}",
            method = RequestMethod.POST,
            consumes = {"application/json"})
    public void add(@PathVariable("company_id") Integer companyId, @RequestBody Advertisement ad) {
        advertisementService.add(companyId, ad);
    }

    @RequestMapping(path = "/advertisements/{ad_id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("ad_id") Integer id, @RequestBody Advertisement ad) {
        advertisementService.update(id, ad);
    }
}
