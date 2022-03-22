package com.example.starenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExamFragment extends Fragment {

    private CardView exam_lv_esay, exam_lv_medium, exam_lv_hard;

    public ExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        exam_lv_esay = view.findViewById(R.id.exam_lv_esay);
        exam_lv_medium = view.findViewById(R.id.exam_lv_medium);
        exam_lv_hard = view.findViewById(R.id.exam_lv_hard);

        exam_lv_esay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMultipleChoiceActivity("EASY");
            }
        });

        exam_lv_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMultipleChoiceActivity("MEDIUM");
            }
        });

        exam_lv_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMultipleChoiceActivity("HARD");
            }
        });

        return view;
    }

    private void openMultipleChoiceActivity(String level) {
        Intent intent = new Intent(getActivity(), MultipleChoiceActivity.class);
        intent.putExtra("EXAM_LEVEL", level);
        getActivity().startActivity(intent);
    }
}