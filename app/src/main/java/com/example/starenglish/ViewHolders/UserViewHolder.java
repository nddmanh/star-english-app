package com.example.starenglish.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.R;

public class UserViewHolder extends RecyclerView.ViewHolder{
    public TextView tv_lb_username, tv_lb_score, tv_lb_rank;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_lb_username = itemView.findViewById(R.id.tv_lb_username);
        tv_lb_score = itemView.findViewById(R.id.tv_lb_score);
        tv_lb_rank = itemView.findViewById(R.id.tv_lb_rank);
    }
}
