package com.codecool.sample.repository;

import com.codecool.sample.model.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Integer> {

    List<Advertisement> findAll();

    Optional<Advertisement> findById(Integer id);

}
