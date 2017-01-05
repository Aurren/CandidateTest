package com.example.aurren.candidatetest.Summary;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aurren.candidatetest.Detail.DetailFragment;
import com.example.aurren.candidatetest.Models.Post.Post;
import com.example.aurren.candidatetest.Models.User.User;
import com.example.aurren.candidatetest.R;
import com.example.aurren.candidatetest.Services.API;
import com.example.aurren.candidatetest.Services.NetworkService;

import java.util.List;

/**
 * Created by Aurren on 03/01/2017.
 */

public class SummaryFragment extends Fragment implements SummaryInterface.View{

    SummaryPresenter presenter;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Post> posts;
    List<User> users;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SummaryPresenter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.post_list);

        recyclerView.setLayoutManager(layoutManager);
        presenter.init(this);
        presenter.getPosts();

    }

    @Override
    public void setPosts(List<Post> posts) {
        this.posts = posts;
        presenter.getUsers();
    }

    @Override
    public void setUsers(List<User> users) {
        this.users = users;
        adapter = new PostListAdapter(posts, getActivity(), this, users);
        recyclerView.setAdapter(adapter);
    }

    public void CreateDetail(int userID, String text, int postID, String postTitle, String userIMGURL){
        android.app.FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount()<=0){
            Log.i("Creating detail"," ");
            Fragment fragment = new DetailFragment();
            Bundle data = new Bundle();
            data.putInt("userID", userID);
            data.putString("text", text);
            data.putInt("postID", postID);
            data.putString("title", postTitle);
            data.putString("userIMGURL", userIMGURL);
            fragment.setArguments(data);

            android.app.FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_holder, fragment);
            ft.addToBackStack("detail");
            ft.commit();
        }

    }
}
