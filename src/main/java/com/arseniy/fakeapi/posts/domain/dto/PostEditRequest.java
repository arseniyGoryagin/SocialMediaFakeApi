package com.arseniy.fakeapi.posts.domain.dto;


import lombok.Data;

@Data
public class PostEditRequest {


    private Long id;

    private Long userId;
    private Long likes;
    private String caption;
    private String imgUrl;

    private Boolean edited;

}

