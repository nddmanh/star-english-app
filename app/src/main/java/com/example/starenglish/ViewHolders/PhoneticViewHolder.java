package com.example.starenglish.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_phonetic;
    public ImageButton img_btn_audio;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_phonetic = itemView.findViewById(R.id.tv_phonetic);
        img_btn_audio = itemView.findViewById(R.id.img_btn_audio);
    }
}
