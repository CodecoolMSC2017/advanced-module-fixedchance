package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.PostAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostAnswerService extends AbstractService {

    public List<PostAnswer> getAll() {
        return postAnswerRepository.findAll(); }

    public PostAnswer getOne(Integer id) {
        return postAnswerRepository.getOne(id);
    }

    public void add(PostAnswer answer) { postAnswerRepository.save(answer);}

    public void delete(Integer id) { postAnswerRepository.deleteById(id);}
}
