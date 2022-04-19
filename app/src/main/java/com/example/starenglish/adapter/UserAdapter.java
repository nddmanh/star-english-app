package com.example.starenglish.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.R;
import com.example.starenglish.ViewHolders.UserViewHolder;
import com.example.starenglish.model.DataUserLeaderboard;
import com.example.starenglish.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<DataUserLeaderboard> mListUser;

    public void setData(List<DataUserLeaderboard> list) {
        this.mListUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        DataUserLeaderboard user = mListUser.get(position);
        if (user == null) {
            return;
        }
        holder.tv_lb_username.setText(user.getFullname() + "");
        holder.tv_lb_score.setText("Score: " + user.getScore());
        holder.tv_lb_rank.setText("" + (position + 1));
    }

    @Override
    public int getItemCount() {
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }
}
