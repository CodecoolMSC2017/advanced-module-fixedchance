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

    public Post add(Post post) { return postRepository.save(post);}

    public void delete(Integer id) { postRepository.deleteById(id);}

    public void update(Integer id) {
        Post post = postRepository.getOne(id);
        post.setRating(post.getRating() + 1);
        postRepository.save(post);}

    public void decrement(Integer id) {
        Post post = postRepository.getOne(id);
        post.setRating(post.getRating() - 1);
        postRepository.save(post);}

    public List<Post> findAllByTopic(PostTopic topic) { return postRepository.findAllByTopics(topic);}

}
