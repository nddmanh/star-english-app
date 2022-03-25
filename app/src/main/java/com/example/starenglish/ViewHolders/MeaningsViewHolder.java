package com.example.starenglish.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_partsOfSpeech;
    public RecyclerView recycler_definitions;

    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_partsOfSpeech = itemView.findViewById(R.id.tv_partsOfSpeech);
        recycler_definitions = itemView.findViewById(R.id.recycler_definitions);

    }
}
