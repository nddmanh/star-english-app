package com.example.starenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.starenglish.api.ApiService;
import com.example.starenglish.data.DataLocalManager;
import com.example.starenglish.model.LoginRequest;
import com.example.starenglish.model.LoginResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.android.gms.tasks.Task;

public class LoginFragment extends Fragment {

    private View mView;
    private EditText input_login_username, input_login_password;
    private Button btn_login;
    private LinearLayout signin_gg_btn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_login, container, false);

        btn_login = mView.findViewById(R.id.btn_login);
        signin_gg_btn = mView.findViewById(R.id.signin_gg_btn);
        input_login_username = mView.findViewById(R.id.input_login_username);
        input_login_password = mView.findViewById(R.id.input_login_password);

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

//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this, gso);

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if(acct!=null){
//            navigateToSecondActivity();
//        }

        signin_gg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clcick", Toast.LENGTH_SHORT).show();
                signinWithGoogle();
            }
        });

        return mView;
    }

    private void signinWithGoogle() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            } catch (ApiException e) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        ApiService.apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Gson gson = new Gson();
                LoginResponse loginResponse = response.body();
                if (loginResponse != null) {
                    String message = loginResponse.getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    if (loginResponse.getDataLogin().getAccessToken().isEmpty()) {
                        Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (loginResponse.getStatusCode() == 200) {
                        String accessToken = loginResponse.getDataLogin().getAccessToken();
                        DataLocalManager.setAccessToken(accessToken);

                        // Change activity
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Call api error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}