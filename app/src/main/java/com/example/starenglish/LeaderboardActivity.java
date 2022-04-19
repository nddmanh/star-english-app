package com.example.starenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.starenglish.adapter.PaginationScrollListener;
import com.example.starenglish.adapter.UserAdapter;
import com.example.starenglish.api.ApiService;
import com.example.starenglish.api.OnFetchDataLeaderboardListener;
import com.example.starenglish.api.OnFetchDataListener;
import com.example.starenglish.api.RequestManager;
import com.example.starenglish.data.DataLocalManager;
import com.example.starenglish.model.DataUserLeaderboard;
import com.example.starenglish.model.LeaderboardResponse;
import com.example.starenglish.model.LoginRequest;
import com.example.starenglish.model.LoginResponse;
import com.example.starenglish.model.User;
import com.example.starenglish.model.dictionary.APIResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private List<DataUserLeaderboard> mListUser = new ArrayList<>();
    private ProgressBar progressBar;

    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPage = 5;

    private final int LIMIT_RECORD = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        rcvUser = findViewById(R.id.rcv_user);
        progressBar = findViewById(R.id.progress_bar);
        userAdapter = new UserAdapter();
        rcvUser.setAdapter(userAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(itemDecoration);
        rcvUser.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                progressBar.setVisibility(View.VISIBLE);
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
        getListUser();
    }

    private void loadNextPage() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getListUser();
                userAdapter.notifyDataSetChanged();

                isLoading = false;
                progressBar.setVisibility(View.GONE);
                if (currentPage == totalPage) {
                    isLastPage = true;
                }
            }
        }, 1000);
    }

    private void getListUser() {
        RequestManager manager = new RequestManager(LeaderboardActivity.this);
        manager.getUserLeaderboard(listener, currentPage, LIMIT_RECORD);
    }

    private final OnFetchDataLeaderboardListener listener = new OnFetchDataLeaderboardListener() {
        @Override
        public void onFetchData(LeaderboardResponse leaderboardResponse, String message) {
            if (leaderboardResponse == null) {
                Toast.makeText(LeaderboardActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.e("onFetchData: ", new Gson().toJson(leaderboardResponse.getDataLeaderboard().getUsers()));
            totalPage = leaderboardResponse.getDataLeaderboard().getTotalPages();
            mListUser.addAll(leaderboardResponse.getDataLeaderboard().getUsers());
            userAdapter.setData(mListUser);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(LeaderboardActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}