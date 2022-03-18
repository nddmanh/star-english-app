package com.example.starenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.starenglish.api.ApiService;
import com.example.starenglish.data.DataLocalManager;
import com.example.starenglish.model.LoginRequest;
import com.example.starenglish.model.LoginResponse;
import com.example.starenglish.model.SignupRequest;
import com.example.starenglish.model.SignupResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {

    private View mView;
    private Button btn_signup;
    private EditText input_register_username,
                    input_register_password,
                    input_register_cfpassword,
                    input_register_fullname,
                    input_register_school,
                    input_register_age;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_signup, container, false);

        btn_signup = mView.findViewById(R.id.btn_signup);
        input_register_username = mView.findViewById(R.id.input_register_username);
        input_register_password = mView.findViewById(R.id.input_register_password);
        input_register_cfpassword = mView.findViewById(R.id.input_register_cfpassword);
        input_register_fullname = mView.findViewById(R.id.input_register_fullname);
        input_register_school = mView.findViewById(R.id.input_register_school);
        input_register_age = mView.findViewById(R.id.input_register_age);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = input_register_username.getText().toString();
                String password = input_register_password.getText().toString();
                String cfpassword = input_register_cfpassword.getText().toString();
                String fullname = input_register_fullname.getText().toString();
                String school = input_register_school.getText().toString();
                String age = input_register_age.getText().toString();

                if (validate_signup(username, password, cfpassword, fullname, school, age)) {
                    int age_num = Integer.parseInt(age);
                    signup(username, password, fullname, school, age_num);
                }
            }
        });

        return mView;
    }

    private void signup(String username, String password, String fullname, String school, int age) {
        SignupRequest signupRequest = new SignupRequest(username, password, fullname, school, age);

        ApiService.apiService.signup(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse signupResponse = response.body();
                if (signupResponse != null) {
                    String message = signupResponse.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    if (signupResponse.getStatusCode() == 200) {
                        String accessToken = signupResponse.getDataLogin().getAccessToken();
                        DataLocalManager.setAccessToken(accessToken);

                        // Change activity
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Username is exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Call api error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate_signup(String username, String password, String cfpassword, String fullname, String school, String age) {
        if (username.isEmpty() || password.isEmpty() || cfpassword.isEmpty() || fullname.isEmpty() || school.isEmpty() || age.isEmpty()) {
            Toast.makeText(getActivity(), "Please do not leave it blank", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(getActivity(), "Password must be at least 6 charecters", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(cfpassword)) {
            Toast.makeText(getActivity(), "Confirm password is not correct", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isNumeric(age)) {
            Toast.makeText(getActivity(), "Age is number from 3 to 70", Toast.LENGTH_SHORT).show();
            return false;
        }
        int age_num = Integer.parseInt(age);
        if (age_num < 3) {
            Toast.makeText(getActivity(), "You are not old enough to use", Toast.LENGTH_SHORT).show();
            return false;
        } else if (age_num > 70) {
            Toast.makeText(getActivity(), "You are too old to use this app", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}