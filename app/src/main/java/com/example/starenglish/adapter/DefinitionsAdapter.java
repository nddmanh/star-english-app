package com.example.starenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.R;
import com.example.starenglish.ViewHolders.DefinitionViewHolder;
import com.example.starenglish.model.dictionary.Definitions;

import java.util.List;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {
    private Context context;
    private List<Definitions> definitionsList;

    public DefinitionsAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionViewHolder(LayoutInflater.from(context).inflate(R.layout.definitions_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.tv_definition.setText("Definitions: " + definitionsList.get(position).getDefinition());
        holder.tv_example.setText("Example: " + definitionsList.get(position).getExample());

        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.tv_synonyms.setText(synonyms);
        holder.tv_antonyms.setText(antonyms);

        holder.tv_synonyms.setSelected(true);
        holder.tv_antonyms.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
