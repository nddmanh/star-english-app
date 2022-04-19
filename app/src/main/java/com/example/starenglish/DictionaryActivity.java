package com.example.starenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starenglish.adapter.MeanigsAdapter;
import com.example.starenglish.adapter.PhoneticsAdapter;
import com.example.starenglish.api.OnFetchDataListener;
import com.example.starenglish.api.RequestManager;
import com.example.starenglish.data.DataLocalManager;
import com.example.starenglish.model.dictionary.APIResponse;

public class DictionaryActivity extends AppCompatActivity {

    SearchView search_view;
    TextView tv_word;
    RecyclerView recycler_phonetics, recycler_meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeanigsAdapter meanigsAdapter;
    Button btn_back_dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        btn_back_dictionary = findViewById(R.id.btn_back_dictionary);

        search_view = findViewById(R.id.search_view);
        tv_word = findViewById(R.id.tv_word);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        recycler_meanings = findViewById(R.id.recycler_meanings);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading... ");
        progressDialog.show();
        RequestManager manager = new RequestManager(DictionaryActivity.this);
        manager.getWordMeaning(listener, "hello");

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(DictionaryActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btn_back_dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse == null) {
                Toast.makeText(DictionaryActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(DictionaryActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        tv_word.setText("Word: " + apiResponse.getWord());

        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        recycler_phonetics.setAdapter(phoneticsAdapter);

        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meanigsAdapter = new MeanigsAdapter(this, apiResponse.getMeanings());
        recycler_meanings.setAdapter(meanigsAdapter);
    }
}