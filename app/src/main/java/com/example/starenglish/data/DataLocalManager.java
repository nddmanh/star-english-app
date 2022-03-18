package com.example.starenglish.data;

import android.content.Context;

import com.example.starenglish.model.User;
import com.google.gson.Gson;

public class DataLocalManager {
    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setAccessToken(String accessToken) {
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_ACCESS_TOKEN, accessToken);
    }

    public static String getAccessToken() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_ACCESS_TOKEN);
    }

    public static void setUser(User user) {
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_OBJECT_USER, strJsonUser);
    }

    public static User getUser() {
        String strJsonUser = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strJsonUser, User.class);
        return user;
    }
}
