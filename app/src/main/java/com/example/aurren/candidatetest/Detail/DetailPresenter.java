package com.example.aurren.candidatetest.Detail;

import android.app.Fragment;
import android.util.Log;

import com.example.aurren.candidatetest.Models.Comment.Comment;
import com.example.aurren.candidatetest.Services.API;
import com.example.aurren.candidatetest.Services.NetworkService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Aurren on 04/01/2017.
 */

public class DetailPresenter implements DetailInterface.Presenter {
    int commentCount = 0;
    CompositeSubscription subscription = new CompositeSubscription();
    API api;
    DetailFragment fragment;

    public DetailPresenter(DetailFragment fragment){
        api = NetworkService.Initialise();
        this.fragment = fragment;
    }

    @Override
    public void getCommentCount(int postID) {

        subscription.add(api.getComments(postID)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Comment Count Error", e.getMessage());
                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        if (comments!=null){
                            fragment.setCommentCount(comments.size());
                        }
                        else{
                            Log.e("Comment Error", "No Comments!");
                        }

                    }
                }));
    }
}
