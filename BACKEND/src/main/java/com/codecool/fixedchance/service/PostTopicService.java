package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Post;
import com.codecool.fixedchance.domain.PostTopic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTopicService extends AbstractService {

    public List<PostTopic> getAll() {
        return postTopicRepository.findAll(); }

    public PostTopic getOne(Integer id) {
        return postTopicRepository.getOne(id);
    }

    public void add(PostTopic topic, Integer postId) {
        Post post = postRepository.getOne(postId);
        topic.setPost(post);
        postTopicRepository.save(topic);}

    public void delete(Integer id) { postTopicRepository.deleteById(id);}
}
