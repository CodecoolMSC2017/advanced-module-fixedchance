package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SimpleUserRepository extends JpaRepository<SimpleUser, Integer> {

    List<SimpleUser> findAll();

    Optional<SimpleUser> findById(Integer id);

    SimpleUser findByEmail(String email);

    SimpleUser findByUserId(Integer id);

    SimpleUser findUsernameById(Integer id);
}

