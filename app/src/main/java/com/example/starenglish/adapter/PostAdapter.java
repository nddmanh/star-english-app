package com.example.starenglish.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starenglish.OnePostActivity;
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

        holder.tvTitlePost.setText(post.getTitle());
        holder.tvDescPost.setText(post.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OnePostActivity.class);
                intent.putExtra("POST_TITLE", post.getTitle());
                intent.putExtra("POST_CREATEDAT", post.getCreatedAt());
                intent.putExtra("POST_CONTENT", post.getContent());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListPost != null) {
            return mListPost.size();
        }
        return 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitlePost, tvDescPost;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitlePost = itemView.findViewById(R.id.tv_title_post);
            tvDescPost = itemView.findViewById(R.id.tv_content_desc);
        }
    }

}
