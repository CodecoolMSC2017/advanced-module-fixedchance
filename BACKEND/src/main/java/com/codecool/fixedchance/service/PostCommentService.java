package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.PostComment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService extends AbstractService {

    public List<PostComment> getAll() {
        return postCommentRepository.findAll(); }

    public PostComment getOne(Integer id) {
        return postCommentRepository.getOne(id);
    }

    public void add(PostComment comment) { postCommentRepository.save(comment);}

    public void delete(Integer id) { postCommentRepository.deleteById(id);}

}
