package com.example.starenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DictionaryFragment extends Fragment {
    private CardView card_dictionany_e_e;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        card_dictionany_e_e = view.findViewById(R.id.card_dictionany_e_e);

        card_dictionany_e_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DictionaryActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}