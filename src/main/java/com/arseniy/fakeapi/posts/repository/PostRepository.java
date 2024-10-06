package com.arseniy.fakeapi.posts.repository;


import com.arseniy.fakeapi.posts.domain.model.Post;
import com.arseniy.fakeapi.user.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Modifying
    @Transactional
    @Query(value = "Update posts_table set " +
            "likes = :likes " +
            "userId = :userId " +
            "caption = :caption" +
            "imgUrl = :imgUrl" +
            "where id =:id", nativeQuery = true)
    Post updatePost(@Param("caption") String caption,
                    @Param("imgUrl") String imgUrl,
                    @Param("likes") Long likes,
                    @Param("userId") Long userId,
                    @Param("id") Long id);



    @Query(value = "Select all from posts_table limit :limit offset :offset", nativeQuery = true)
    List<Post> getPosts(@Param("offset") Long offset, @Param("limit") Long limit );



}
