package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Post;
import com.codecool.fixedchance.domain.PostTopic;
import com.codecool.fixedchance.domain.SimpleUser;
import com.codecool.fixedchance.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class PostService extends AbstractService {

    public List<Post> getAll() {
        return postRepository.findAllByOrderByIdAsc();
    }

    public Post getOne(Integer id) {
        return postRepository.getOne(id);
    }

    public Post add(Post post) {
        User user = userRepository.findByUsername(post.getUserName());
        SimpleUser simpleUser = simpleUserRepository.findByUserId(user.getId());
        post.setUser(simpleUser);
        return postRepository.save(post);}

    public void delete(Integer id) { postRepository.deleteById(id);}

    public void update(Integer id, Boolean rating) {
        Post post = postRepository.getOne(id);
        if (rating) {
            post.setRating(post.getRating() + 1);
        } else {
            post.setRating(post.getRating() - 1);
        }
        postRepository.save(post);}

    public List<Post> findAllByTopic(PostTopic topic) { return postRepository.findAllByTopics(topic);}

}
