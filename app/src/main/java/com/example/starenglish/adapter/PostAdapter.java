package com.example.starenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.model.Post;
import com.example.starenglish.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context mContext;
    private List<Post> mListPost;

    public PostAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Post> list) {
        this.mListPost = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = mListPost.get(position);
        if (post == null) {
            return;
        }

        holder.imgPost.setImageResource(post.getResourceId());
        holder.tvName.setText(post.getName());
    }

    @Override
    public int getItemCount() {
        if (mListPost != null) {
            return mListPost.size();
        }
        return 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPost;
        private TextView tvName;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPost = itemView.findViewById(R.id.img_post);
            tvName = itemView.findViewById(R.id.tv_title_post);
        }
    }

}
