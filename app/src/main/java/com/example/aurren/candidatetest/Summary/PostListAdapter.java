package com.example.aurren.candidatetest.Summary;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aurren.candidatetest.Models.Post.Post;
import com.example.aurren.candidatetest.Models.User.User;
import com.example.aurren.candidatetest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aurren on 04/01/2017.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {
    List<Post> posts;
    List<User> users;
    Context context;
    SummaryFragment fragment;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView postTitle;
        ImageView postImage;
        TextView postUser;
        CardView postCard;
        public ViewHolder(View itemView) {
            super(itemView);
            postImage = (ImageView) itemView.findViewById(R.id.post_picture);
            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postCard = (CardView) itemView.findViewById(R.id.post_card);
            postUser = (TextView) itemView.findViewById(R.id.post_user);
        }
    }

    public PostListAdapter(List<Post> posts, Context context, SummaryFragment fragment, List<User> users){
        this.posts = posts;
        this.context = context;
        this.fragment = fragment;
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int userID = posts.get(position).getUserId();
        User user = users.get(userID);
        holder.postUser.setText(user.getUsername());
        holder.postTitle.setText(posts.get(position).getTitle());
        final String userIMGURL = "https://api.adorable.io/avatars/64/"+String.valueOf(user.getEmail())+".png";
        Log.i("User IMG URL", position+" "+userIMGURL+" "+"("+userID+")");
        Picasso.with(context).load(userIMGURL).into(holder.postImage);

        holder.postCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pass position and create the new Detail fragment.
                fragment.CreateDetail(posts.get(position).getUserId()
                        ,posts.get(position).getBody()
                        ,posts.get(position).getId()
                        ,posts.get(position).getTitle()
                        ,userIMGURL);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
