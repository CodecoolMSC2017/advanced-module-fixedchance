package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.PostUserIdentity;
import com.codecool.fixedchance.domain.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostVoteRepository extends JpaRepository<PostVote, PostUserIdentity> {

    List<PostVote> findAll();
}
