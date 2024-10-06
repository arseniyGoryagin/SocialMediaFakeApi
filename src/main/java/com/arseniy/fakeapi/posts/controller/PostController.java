package com.arseniy.fakeapi.posts.controller;


import com.arseniy.fakeapi.exceptions.NotFoundException;
import com.arseniy.fakeapi.posts.domain.dto.PostAddRequest;
import com.arseniy.fakeapi.posts.domain.dto.PostEditRequest;
import com.arseniy.fakeapi.posts.domain.model.Post;
import com.arseniy.fakeapi.posts.service.PostService;
import com.arseniy.fakeapi.user.domain.dto.UserAddRequest;
import com.arseniy.fakeapi.user.domain.dto.UserEditRequest;
import com.arseniy.fakeapi.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping()
    public ResponseEntity<List<Post>> getPosts(@RequestParam("offset") Long offset, @RequestParam("limit") Long limit){
        var posts = postService.getPosts(offset, limit);
        return new ResponseEntity<>( posts, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Post> addPost(@RequestBody PostAddRequest request){


        var post = postService.addPost(request.getCaption(), request.getImgUrl(), request.getLikes(), request.getUserId());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) throws NotFoundException {

        var post = postService.getPost(id);

        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){

       postService.removePost(id);

        return new ResponseEntity<>("Post removed", HttpStatus.OK);

    }


    @PutMapping("{id}")
    public ResponseEntity<Post> editPost(@RequestBody PostEditRequest request) throws NotFoundException {

        var post = postService.editUser(request.getUserId(), request.getId(), request.getLikes(), request.getCaption(), request.getImgUrl());

        return new ResponseEntity<>(post, HttpStatus.OK);

    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(NotFoundException e){
        return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUserNotFoundException(Exception e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
