package com.codecool.fixedchance.service;

import com.codecool.fixedchance.domain.Post;
import com.codecool.fixedchance.domain.PostUserIdentity;
import com.codecool.fixedchance.domain.PostVote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostVoteService extends AbstractService {

    public List<PostVote> findAll() {return postVoteRepository.findAll();}

    public void add(PostVote postVote) {
        PostUserIdentity puIdentity = new PostUserIdentity();
        puIdentity.setPostId(postVote.getPostId());
        puIdentity.setVoterId(postVote.getVoterId());
        postVote.setPostUserIdentity(puIdentity);
        postVoteRepository.save(postVote);}

    public void delete(Integer id) {
        Post post = postRepository.getOne(id);
        postVoteRepository.deleteAllByPostId(id);
    }
}
