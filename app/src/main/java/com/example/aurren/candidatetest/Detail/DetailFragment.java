package com.example.aurren.candidatetest.Detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aurren.candidatetest.R;

/**
 * Created by Aurren on 04/01/2017.
 */

public class DetailFragment extends Fragment implements DetailInterface.View {
    int post_userID;
    int post_ID;
    String post_body;
    String post_title_text;

    DetailPresenter presenter;

    TextView post_commentCount;
    TextView post_text;
    TextView post_title;
    ImageView post_image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailPresenter(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle data = new Bundle();
        data = getArguments();
        post_title_text = data.getString("title");
        post_body = data.getString("text");
        post_ID = data.getInt("postID");
        post_userID = data.getInt("userID");

        post_commentCount = (TextView) getActivity().findViewById(R.id.detail_commentCount);
        presenter.getCommentCount(post_ID);


        post_text = (TextView) getActivity().findViewById(R.id.detail_text);
        post_text.setText(post_body);

        post_title = (TextView) getActivity().findViewById(R.id.detail_title);
        post_title.setText(post_title_text);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_detail_fragment, container, false);
        return view;
    }

    @Override
    public void setCommentCount(int number) {
        post_commentCount.setText("Comments: "+number);
    }
}
