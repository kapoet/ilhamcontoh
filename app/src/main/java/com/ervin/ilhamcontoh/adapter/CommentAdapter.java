package com.ervin.ilhamcontoh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ervin.ilhamcontoh.R;
import com.ervin.ilhamcontoh.model.Comment;
import com.ervin.ilhamcontoh.model.Post;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>  {
    private Context context;
    List<Comment> commentItem;

    public CommentAdapter(List<Comment> commentItem, Context context) {
        super();
        this.commentItem = commentItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Comment commentItems = commentItem.get(i) ;
        viewHolder.commentName.setText(commentItems.getName());
        viewHolder.commentBody.setText(commentItems.getBody());
        viewHolder.commentEmail.setText(commentItems.getEmail());
    }

    @Override
    public int getItemCount() {
        return commentItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView commentName;
        public TextView commentEmail;
        public TextView commentBody;

        public ViewHolder(final View itemView) {
            super(itemView);

            commentBody = itemView.findViewById(R.id.tv_body);
            commentEmail = itemView.findViewById(R.id.tv_email);
            commentName = itemView.findViewById(R.id.tv_name);

        }
    }
}
