package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Post;
import com.codecool.fixedchance.domain.PostTopic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class PostService extends AbstractService {

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getOne(Integer id) {
        return postRepository.getOne(id);
    }

    public void add(Post post) { postRepository.save(post);}

    public void delete(Integer id) { postRepository.deleteById(id);}

    public List<Post> findAllByTopic(PostTopic topic) { return postRepository.findAllByTopics(topic);}

}
