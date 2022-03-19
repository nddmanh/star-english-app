package com.example.starenglish.api;

import com.example.starenglish.model.LoginRequest;
import com.example.starenglish.model.LoginResponse;
import com.example.starenglish.model.PostResponse;
import com.example.starenglish.model.SignupRequest;
import com.example.starenglish.model.SignupResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    String URL_SERVER_STAR_ENGLISH = "http://localhost:5000/api/";
    //192.168.56.1

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder().baseUrl("http://192.168.56.1:5000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);

    // Auth
    @POST("v1/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("v1/auth/register")
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);

    // Post
    @GET("v1/posts")
    Call<PostResponse> getPosts();
}
