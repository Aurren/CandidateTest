package com.example.aurren.candidatetest.Summary;

import android.util.Log;

import com.example.aurren.candidatetest.Models.Post.Post;
import com.example.aurren.candidatetest.Models.User.User;
import com.example.aurren.candidatetest.Services.API;
import com.example.aurren.candidatetest.Services.NetworkService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Aurren on 03/01/2017.
 */

public class SummaryPresenter implements SummaryInterface.Presenter{
    API api;
    CompositeSubscription subscription = new CompositeSubscription();
    SummaryFragment fragment;

    @Override
    public void init(SummaryFragment fragment) {
        this.fragment = fragment;
        api = NetworkService.Initialise();
    }

    @Override
    public void getPosts() {
        subscription.add(api.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .onBackpressureBuffer()
            .subscribe(new Observer<List<Post>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.e("GetPosts Error", e.getMessage());
                }

                @Override
                public void onNext(List<Post> posts) {
                    if ((posts!=null)&&(posts.size()!=0)){
                        Log.i("Posts found", posts.toString());
                        fragment.setPosts(posts);
                    }
                    else{
                        Log.e("GetPosts Error", "bad data");
                    }
                }
            }));
    }

    @Override
    public void getUser(int id) {
        subscription.add(api.getUser(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .onBackpressureBuffer()
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("GetUser Error", e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        if (user!=null){
                            Log.i("User found", user.toString());

                        }
                        else{
                            Log.e("GetUser Error", "bad data");
                        }
                    }
                }));
    }
}
