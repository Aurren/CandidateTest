package com.example.aurren.candidatetest.Summary;

import com.example.aurren.candidatetest.Models.Post.Post;
import com.example.aurren.candidatetest.Models.User.User;

import java.util.List;

/**
 * Created by Aurren on 03/01/2017.
 */

public interface SummaryInterface {
    interface Presenter{
        void init(SummaryFragment fragment);
        void getPosts();
        void getUsers();
    }

    interface View{
        void setPosts(List<Post> posts);
        void setUsers(List<User> users);
    }
}
