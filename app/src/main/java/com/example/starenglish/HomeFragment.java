package com.example.starenglish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.starenglish.adapter.PostAdapter;
import com.example.starenglish.model.Post;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcvPost;
    private PostAdapter postAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcvPost = view.findViewById(R.id.rcv_post);
        postAdapter = new PostAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvPost.setLayoutManager(linearLayoutManager);

        postAdapter.setData(getListPost());
        rcvPost.setAdapter(postAdapter);
        return view;
    }

    private List<Post> getListPost() {
        List<Post> list = new ArrayList<>();

        list.add(new Post(R.drawable.avt_1, "Post 1"));
        list.add(new Post(R.drawable.avt_1, "Post 2"));
        list.add(new Post(R.drawable.avt_1, "Post 3"));
        list.add(new Post(R.drawable.avt_1, "Post 4"));
        list.add(new Post(R.drawable.avt_1, "Post 5"));
        return list;
    }
}