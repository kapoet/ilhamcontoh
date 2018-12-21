package com.ervin.ilhamcontoh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ervin.ilhamcontoh.adapter.CommentAdapter;
import com.ervin.ilhamcontoh.adapter.PostAdapter;
import com.ervin.ilhamcontoh.model.Comment;
import com.ervin.ilhamcontoh.model.Post;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    CommentAdapter mAdapter;
    RecyclerView rvPost;
    RecyclerView.LayoutManager mLayoutManager;
    List<Comment> commentList;
    int idpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        idpost=intent.getIntExtra("idpost",1);
        rvPost = findViewById(R.id.rv_comment);

        rvPost.setHasFixedSize(true);
        commentList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        rvPost.setLayoutManager(mLayoutManager);

        mAdapter = new CommentAdapter(commentList, this);
        rvPost.setAdapter(mAdapter);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        String url = "http://jsonplaceholder.typicode.com/posts/"+idpost+"/comments";
        Log.d("isinya", "onCreate: "+url);
        Ion.with(this)
                .load("GET", url)
                .asJsonArray()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonArray>>() {
                    @Override
                    public void onCompleted(Exception e, com.koushikdutta.ion.Response<JsonArray> result) {
                        try {
                            JsonArray jsonArray = result.getResult();
                            for (int i = 0; i<jsonArray.size();i++){
                                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                                Comment commentTemp = new Comment();
                                commentTemp.setBody(jsonObject.get("body").getAsString());
                                commentTemp.setEmail(jsonObject.get("email").getAsString());
                                commentTemp.setName(jsonObject.get("name").getAsString());
                                commentList.add(commentTemp);
                                mAdapter.notifyDataSetChanged();
                            }
                            progress.dismiss();
                        } catch (Exception ex) {
                            System.out.println("eror apani: " + ex);
                            progress.dismiss();
                        }
                    }
                });
    }
}
