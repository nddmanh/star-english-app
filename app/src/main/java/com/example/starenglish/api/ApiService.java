package com.example.starenglish.api;

import com.example.starenglish.model.CurrentUserResponse;
import com.example.starenglish.model.LeaderboardResponse;
import com.example.starenglish.model.LoginGoogleRequest;
import com.example.starenglish.model.LoginRequest;
import com.example.starenglish.model.LoginResponse;
import com.example.starenglish.model.PostResponse;
import com.example.starenglish.model.QuestionResponse;
import com.example.starenglish.model.SignupRequest;
import com.example.starenglish.model.SignupResponse;
import com.example.starenglish.model.UpdateScoreUserRequest;
import com.example.starenglish.model.UpdateScoreUserResponse;
import com.example.starenglish.model.dictionary.APIResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String URL_SERVER_STAR_ENGLISH = "https://star-english.herokuapp.com/api/";
    // 192.168.56.1 - http://localhost:5000/api/
    // https://star-english.herokuapp.com/api/

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder().baseUrl(URL_SERVER_STAR_ENGLISH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);

    // Auth
    @POST("v1/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("v1/auth/register")
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);

    @GET("v1/auth")
    Call<CurrentUserResponse> getCurrentUser(@Header("authorization") String auth);

    @POST("v1/auth/google")
    Call<LoginResponse> loginGoogle(@Body LoginGoogleRequest loginGoogleRequest);

    // Post
    @GET("v1/posts")
    Call<PostResponse> getPosts();

    // Question
    @GET("v1/questions")
    Call<QuestionResponse> getQuestion(@Query("page") int page,
                                       @Query("limit") int limit,
                                       @Query("level") String level);

    // User
    @PATCH("v1/users/update-score")
    Call<UpdateScoreUserResponse> updateScoreUser(@Header("authorization") String auth,
                                                  @Body UpdateScoreUserRequest updateScoreUserRequest);
    @GET("v1/users/leaderboard")
    Call<LeaderboardResponse> getLeaderboard(@Query("page") int page,
                                             @Query("limit") int limit);
    // Dictionary
    @GET("v1/dictionary/{word}")
    Call<List<APIResponse>> callMeanings(@Path("word") String word);

}
