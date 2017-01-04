package com.example.aurren.candidatetest.Detail;

/**
 * Created by Aurren on 04/01/2017.
 */

public interface DetailInterface {

    interface View{
        void setCommentCount(int number);
    }
    interface Presenter{
        void getCommentCount(int postID);
    }
}
