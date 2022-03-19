package com.example.starenglish;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class OnePostActivity extends AppCompatActivity {

    private TextView tv_one_post_title, tv_one_post_createdAt, tv_one_post_content;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_post);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        tv_one_post_title = findViewById(R.id.tv_one_post_title);
        tv_one_post_createdAt = findViewById(R.id.tv_one_post_createdAt);
        tv_one_post_content = findViewById(R.id.tv_one_post_content);

        String datetimeInString = (String) bundle.get("POST_CREATEDAT") ;
        Date date = Date.from( Instant.parse( datetimeInString ));
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);

        tv_one_post_title.setText( (String) bundle.get("POST_TITLE") );
        tv_one_post_createdAt.setText("Post at: " + strDate);
        tv_one_post_content.setText( (String) bundle.get("POST_CONTENT") );
    }

}