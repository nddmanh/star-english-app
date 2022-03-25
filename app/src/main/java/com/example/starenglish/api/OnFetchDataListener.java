package com.example.starenglish.api;

import com.example.starenglish.model.dictionary.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
