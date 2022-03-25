package com.example.starenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.R;
import com.example.starenglish.ViewHolders.MeaningsViewHolder;
import com.example.starenglish.model.dictionary.Meanings;

import java.util.List;

public class MeanigsAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {
    private Context context;
    private List<Meanings> meaningsList;

    public MeanigsAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningsViewHolder holder, int position) {
        holder.tv_partsOfSpeech.setText("Parts of Speech: "+ meaningsList.get(position).getPartOfSpeech());
        holder.recycler_definitions.setHasFixedSize(true);
        holder.recycler_definitions.setLayoutManager(new GridLayoutManager(context, 1));
        DefinitionsAdapter definitionsAdapter = new DefinitionsAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recycler_definitions.setAdapter(definitionsAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
