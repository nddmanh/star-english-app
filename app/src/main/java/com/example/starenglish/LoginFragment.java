package com.example.starenglish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starenglish.api.ApiService;
import com.example.starenglish.model.LoginRequest;
import com.example.starenglish.model.LoginResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private View mView;
    private EditText input_login_username, input_login_password;
    private Button btn_login;

    private TextView tv_test_login;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_login, container, false);

        btn_login = mView.findViewById(R.id.btn_login);
        input_login_username = mView.findViewById(R.id.input_login_username);
        input_login_password = mView.findViewById(R.id.input_login_password);

        tv_test_login = mView.findViewById(R.id.tv_test_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = input_login_username.getText().toString();
                String password =  input_login_password.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "Please do not leave it blank", Toast.LENGTH_SHORT).show();
                } else {
                    login(username, password);
                }
            }
        });

        return mView;
    }

    private void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        ApiService.apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Gson gson = new Gson();
                Log.d("res", gson.toJson(response));
                LoginResponse loginResponse = response.body();
                int code = response.code();
                System.out.println(loginResponse);
                System.out.println(code);
                if (loginResponse != null) {
                    String message = loginResponse.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    if (loginResponse.getStatusCode() == 200) {
//                        String accessToken = loginResponse.getDataLogin().getAccessToken();
//                        tv_test_login.setText(accessToken);
                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } else if (!response.isSuccessful()){
                    String errorBody = response.errorBody().toString();

                    System.out.println("errorBody" + errorBody);

                }
//                else {
//                    System.out.println("saiiii3");
//                    Toast.makeText(getActivity(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
//
//                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Call api error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}