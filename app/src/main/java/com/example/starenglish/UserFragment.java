package com.example.starenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.starenglish.api.ApiService;
import com.example.starenglish.data.DataLocalManager;
import com.example.starenglish.model.CurrentUserResponse;
import com.google.gson.Gson;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {

    private TextView tv_user_max_score, tv_user_fullname, tv_user_email;
    private Button btn_user_leaderboard,btn_user_logout;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Mapping UI
        tv_user_max_score = view.findViewById(R.id.tv_user_max_score);
        tv_user_fullname = view.findViewById(R.id.tv_user_fullname);
        tv_user_email = view.findViewById(R.id.tv_user_email);
        btn_user_leaderboard = view.findViewById(R.id.btn_user_leaderboard);
        btn_user_logout = view.findViewById(R.id.btn_user_logout);

        callApiGetCurrentUser();

        btn_user_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLeaderboard();
            }
        });

        btn_user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        return view;
    }

    private void callApiGetCurrentUser() {
        // Validate access token
        String accessToken = DataLocalManager.getAccessToken();

        if (accessToken.isEmpty()) {
            Toast.makeText(getActivity(), "You are not logged in", Toast.LENGTH_SHORT).show();
            openLoginActivity();
            return;
        }

        JWT jwt = new JWT(accessToken);
        Date expiresAt = jwt.getExpiresAt();
        Date now = new Date();
        if (expiresAt.before(now)) {
            Toast.makeText(getActivity(), "You need to login again", Toast.LENGTH_SHORT).show();
            openLoginActivity();
            return;
        } else {
            ApiService.apiService.getCurrentUser("Bearer " + accessToken).enqueue(new Callback<CurrentUserResponse>() {
                @Override
                public void onResponse(Call<CurrentUserResponse> call, Response<CurrentUserResponse> response) {
                    CurrentUserResponse currentUserResponse = response.body();
                    if (currentUserResponse != null && response.code() == 200) {
                        tv_user_max_score.setText("MAX SCORE: " + currentUserResponse.getDataCurrentUser().getScore());
                        tv_user_fullname.setText(currentUserResponse.getDataCurrentUser().getFullname());
                        tv_user_email.setText(currentUserResponse.getDataCurrentUser().getEmail());
                    } else {
                        Toast.makeText(getActivity(), "Can not get info user", Toast.LENGTH_SHORT).show();
                        openLoginActivity();
                        return;
                    }
                }

                @Override
                public void onFailure(Call<CurrentUserResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Call api error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void logout() {
        DataLocalManager.setAccessToken(null);
        Toast.makeText(getActivity(), "Logout succesfully", Toast.LENGTH_SHORT).show();
        openLoginActivity();
        return;
    }

    private void viewLeaderboard() {
        Intent intent = new Intent(getActivity(), LeaderboardActivity.class);
        getActivity().startActivity(intent);
    }

    private void openLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }
}