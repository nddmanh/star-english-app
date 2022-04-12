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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.starenglish.api.ApiService;
import com.example.starenglish.data.DataLocalManager;
import com.example.starenglish.model.LoginGoogleRequest;
import com.example.starenglish.model.LoginRequest;
import com.example.starenglish.model.LoginResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
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

        String serverClientId = getString(R.string.server_client_id);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestEmail()
                .requestIdToken(serverClientId)
                .build();
        gsc = GoogleSignIn.getClient(getActivity(), gso);

        signin_gg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signinWithGoogle();
            }
        });

        return mView;
    }

    private void signinWithGoogle() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String tokenGoogle = account.getIdToken();
                loginGoogle(tokenGoogle);
            } catch (ApiException e) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void changeToMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
    }

    private void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        ApiService.apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
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
                        changeToMainActivity();
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

    private void loginGoogle(String tokenGoogle) {
        LoginGoogleRequest loginGoogleRequest = new LoginGoogleRequest(tokenGoogle);

        ApiService.apiService.loginGoogle(loginGoogleRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
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
                        changeToMainActivity();
                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error! An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Call api error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}