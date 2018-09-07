package com.codecool.fixedchance.controller;

import com.codecool.fixedchance.domain.Advertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertisementController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    @RequestMapping(value = "/advertisements",
            method = RequestMethod.GET)
    public List<Advertisement> getAll() {
        logger.info("returning all advertisements");
        return advertisementService.getAll();
    }

    @RequestMapping("/advertisements/{id}")
    public Advertisement getOne(@PathVariable("id") Integer id) {
        logger.info("getting ad with id {}", id);
        return advertisementService.getOne(id);
    }

    @RequestMapping(path = "/advertisements/{company_id}",
            method = RequestMethod.POST,
            consumes = {"application/json"})
    public Advertisement add(@PathVariable("company_id") Integer companyId, @RequestBody Advertisement ad) {
        logger.info("creating ad for company with id {}, {}", companyId, ad);
        return advertisementService.add(companyId, ad);
    }

    @RequestMapping(path = "/advertisements/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"})
    public void put(@PathVariable("id") Integer id, @RequestBody Advertisement ad) {
        logger.info("updating ad with id {} to {}", id, ad);
        advertisementService.update(id, ad);
    }

    @RequestMapping(path = "/advertisements/{id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        logger.info("deleting ad with id {}", id);
        advertisementService.delete(id);
    }

    @RequestMapping(path = "/companies/{company_id}/advertisements",
            method = RequestMethod.GET,
            consumes = {"application/json"})
    public List<Advertisement> findByCompany(@PathVariable("company_id") Integer companyId) {
        logger.info("returning ads of company with id {}", companyId);
        return advertisementService.findByCompany(companyId);
    }
}
