package com.ervin.ilhamcontoh.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ervin.ilhamcontoh.R;
import com.ervin.ilhamcontoh.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {
    private Context context;
    List<Post> postItem;

    public PostAdapter(List<Post> postItem, Context context) {
        super();
        this.postItem = postItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post postitems = postItem.get(i);
        viewHolder.postBody.setText(postitems.getBody());
        viewHolder.postTitle.setText(postitems.getTitle());
    }

    @Override
    public int getItemCount() {
        return postItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView postTitle;
        public TextView postBody;

        public ViewHolder(final View itemView) {
            super(itemView);

            postTitle = itemView.findViewById(R.id.tv_title);
            postBody = itemView.findViewById(R.id.tv_body);

        }
    }
}
