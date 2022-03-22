package com.example.starenglish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.starenglish.api.ApiService;
import com.example.starenglish.data.DataLocalManager;
import com.example.starenglish.model.Post;
import com.example.starenglish.model.Question;
import com.example.starenglish.model.QuestionResponse;
import com.example.starenglish.model.UpdateScoreUserRequest;
import com.example.starenglish.model.UpdateScoreUserResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultipleChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_multi_level, tv_multi_score;
    private TextView tvQuestion;
    private TextView tvContentQuestion;
    private TextView tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4;

    private List<Question> mListQuestion;
    private Question mQuestion;
    private int currentQuestion = 0;
    private int score = 0;
    private  String accessToken = DataLocalManager.getAccessToken();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        String level = bundle.get("EXAM_LEVEL").toString();

        tv_multi_level = findViewById(R.id.tv_multi_level);
        tv_multi_score = findViewById(R.id.tv_multi_score);
        tv_multi_level.setText("LEVEL: " + level);

        initUI();

        ApiService.apiService.getQuestion(1, 10, level).enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                QuestionResponse questionResponse = response.body();
                if (questionResponse != null && response.code() == 200) {
                    if (questionResponse.getDataQuestion().getQuestions().isEmpty()) {
                        Toast.makeText(MultipleChoiceActivity.this, "Don't have question for this level" , Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        mListQuestion = questionResponse.getDataQuestion().getQuestions();
                        setDataQuestion(mListQuestion.get(currentQuestion));
                    }
                } else {
                    Toast.makeText(MultipleChoiceActivity.this, questionResponse.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                Toast.makeText(MultipleChoiceActivity.this, "Call api error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initUI() {
        tvQuestion = findViewById(R.id.tv_question);
        tvContentQuestion = findViewById(R.id.tv_content_question);
        tvAnswer1 = findViewById(R.id.tv_answer1);
        tvAnswer2 = findViewById(R.id.tv_answer2);
        tvAnswer3 = findViewById(R.id.tv_answer3);
        tvAnswer4 = findViewById(R.id.tv_answer4);

        if (accessToken.isEmpty()) {
            Toast.makeText(MultipleChoiceActivity.this, "You are not logged in", Toast.LENGTH_SHORT).show();
            openLoginActivity();
            return;
        }

        JWT jwt = new JWT(accessToken);
        Date expiresAt = jwt.getExpiresAt();
        Date now = new Date();
        if (expiresAt.before(now)) {
            Toast.makeText(MultipleChoiceActivity.this, "You need to login again", Toast.LENGTH_SHORT).show();
            openLoginActivity();
            return;
        }
    }

    private void setDataQuestion(Question question) {
        if (question == null) return;

        mQuestion = question;
        tv_multi_score.setText("SCORE: " + score);

        tvAnswer1.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer2.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer3.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer4.setBackgroundResource(R.drawable.bg_blue_corner_30);

        String titleQuestion = "Question " + question.getNumber() + "/" + mListQuestion.size();
        tvQuestion.setText(titleQuestion);
        tvContentQuestion.setText(question.getQuestion_content());

        tvAnswer1.setText(question.getAnswer1());
        tvAnswer2.setText(question.getAnswer2());
        tvAnswer3.setText(question.getAnswer3());
        tvAnswer4.setText(question.getAnswer4());

        tvAnswer1.setOnClickListener(this);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_answer1:
                tvAnswer1.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer1, mQuestion, "answer1");
                break;

            case R.id.tv_answer2:
                tvAnswer2.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer2, mQuestion, "answer2");
                break;

            case R.id.tv_answer3:
                tvAnswer3.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer3, mQuestion, "answer3");
                break;

            case R.id.tv_answer4:
                tvAnswer4.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer4, mQuestion, "answer4");
                break;
        }
    }

    private void checkAnswer(final TextView textView, Question question, String choice) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (question.getCorrect_answer().equals(choice)) {
                    score++;
                    textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                    nextQuestion();
                } else {
                    textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                    showAnswerCorrect(question);
                    nextQuestion();
                }
            }
        }, 1000);
    }

    private void showAnswerCorrect(Question question) {
        if (question == null) return;
        if (question.getCorrect_answer().equals("answer1")) {
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (question.getCorrect_answer().equals("answer2")) {
            tvAnswer2.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (question.getCorrect_answer().equals("answer3")) {
            tvAnswer3.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (question.getCorrect_answer().equals("answer4")) {
            tvAnswer4.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
    }

    private void nextQuestion() {
        if (currentQuestion == mListQuestion.size() - 1) {
            //send api update score
            UpdateScoreUserRequest updateScoreUserRequest = new UpdateScoreUserRequest(score);
            ApiService.apiService.updateScoreUser("Bearer " + accessToken, updateScoreUserRequest).enqueue(new Callback<UpdateScoreUserResponse>() {
                @Override
                public void onResponse(Call<UpdateScoreUserResponse> call, Response<UpdateScoreUserResponse> response) {
                    showMessageDialog("You have completed the test! \nYour score: " + score);
                }

                @Override
                public void onFailure(Call<UpdateScoreUserResponse> call, Throwable t) {
                    Toast.makeText(MultipleChoiceActivity.this, "Call api error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            currentQuestion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDataQuestion(mListQuestion.get(currentQuestion));
                }
            }, 1000);
        }
    }

    private void showMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Quit the activity
                dialogInterface.dismiss();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openLoginActivity() {
        Intent intent = new Intent(MultipleChoiceActivity.this, LoginActivity.class);
        MultipleChoiceActivity.this.startActivity(intent);
    }
}