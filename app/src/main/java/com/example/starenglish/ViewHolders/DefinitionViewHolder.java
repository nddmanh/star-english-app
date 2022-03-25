package com.example.starenglish.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_definition, tv_example, tv_synonyms, tv_antonyms;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_definition = itemView.findViewById(R.id.tv_definition);
        tv_example = itemView.findViewById(R.id.tv_example);
        tv_synonyms = itemView.findViewById(R.id.tv_synonyms);
        tv_antonyms = itemView.findViewById(R.id.tv_antonyms);

    }
}
