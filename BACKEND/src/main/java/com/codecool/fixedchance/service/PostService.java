package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class PostService extends AbstractService {

    public List<Post> getAll() {
        return postRepository.findAll(); }
}
