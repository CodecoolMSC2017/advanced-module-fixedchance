package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Post;
import com.codecool.fixedchance.domain.PostTopic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class PostService extends AbstractService {

    public List<Post> getAll() {
        return postRepository.findAll(); }

    public void add(Post post) { postRepository.save(post);}
    
    public List<Post> findAllByTopic(String postTopic) { return postRepository.findAllByPostTopic(postTopic);}

}
