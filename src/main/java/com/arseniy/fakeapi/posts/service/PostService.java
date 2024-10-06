package com.arseniy.fakeapi.posts.service;


import com.arseniy.fakeapi.exceptions.NotFoundException;
import com.arseniy.fakeapi.posts.domain.model.Post;
import com.arseniy.fakeapi.posts.repository.PostRepository;
import com.arseniy.fakeapi.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post addPost(String caption, String imgUrl, Long likes, Long userId){

        var post = Post.builder()
                .caption(caption)
                .edited(false)
                .likes(likes)
                .imgUrl(imgUrl)
                .build();

        return postRepository.save(post);
    }

    public void removePost (Long id){
        postRepository.deleteById(id);
    }

    public Post getPost(Long userId) throws NotFoundException {
        var post = postRepository.findById(userId);
        if(post.isEmpty()){
            throw new NotFoundException();
        }

        return post.get();

    }

    public List<Post> getPosts(Long limit, Long offset) {
        return postRepository.getPosts(limit, offset);
    }

    public Post editUser(Long userId , Long postId, Long likes, String caption, String imgUrl) throws NotFoundException {


        var post = postRepository.findById(userId);
        if(post.isEmpty()){
            throw new NotFoundException();
        }

        return postRepository.updatePost(caption, imgUrl, likes, userId, postId);


    }



}
