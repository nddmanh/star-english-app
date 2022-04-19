package com.example.starenglish.api;

import com.example.starenglish.model.LeaderboardResponse;

public interface OnFetchDataLeaderboardListener {
    void onFetchData(LeaderboardResponse leaderboardResponse, String message);
    void onError(String message);
}
