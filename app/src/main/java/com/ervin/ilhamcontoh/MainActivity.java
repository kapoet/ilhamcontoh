package com.ervin.ilhamcontoh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ervin.ilhamcontoh.adapter.PostAdapter;
import com.ervin.ilhamcontoh.model.Post;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PostAdapter mAdapter;
    RecyclerView rvPost;
    RecyclerView.LayoutManager mLayoutManager;
    List<Post> postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPost = findViewById(R.id.rv_post);

        rvPost.setHasFixedSize(true);
        postList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        rvPost.setLayoutManager(mLayoutManager);

        mAdapter = new PostAdapter(postList, this);
        rvPost.setAdapter(mAdapter);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        String url = "http://jsonplaceholder.typicode.com/posts";
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
                                Post postTemp = new Post();
                                postTemp.setBody(jsonObject.get("body").getAsString());
                                postTemp.setTitle(jsonObject.get("title").getAsString());
                                postList.add(postTemp);
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
