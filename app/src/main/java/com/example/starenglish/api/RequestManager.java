package com.example.starenglish.api;

import android.content.Context;
import android.widget.Toast;

import com.example.starenglish.model.dictionary.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestManager {
    Context context;

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word) {
        Call<List<APIResponse>> call = ApiService.apiService.callMeanings(word);
        try {
            call.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "An error occurred!", Toast.LENGTH_SHORT).show();
        }
    };
}
