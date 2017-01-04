package com.example.aurren.candidatetest.Services;

import com.example.aurren.candidatetest.Models.Comment.Comment;
import com.example.aurren.candidatetest.Models.Post.Post;
import com.example.aurren.candidatetest.Models.User.User;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Aurren on 03/01/2017.
 */

public interface API {

    @GET("/posts/")
    Observable<List<Post>> getPosts();

    @GET("/user/")
    Observable<User> getUser(@Query("id") int id); //user's ID

    @GET("/comments/")
    Observable<List<Comment>> getComments(@Query("postId") int id); //The post's ID
}
