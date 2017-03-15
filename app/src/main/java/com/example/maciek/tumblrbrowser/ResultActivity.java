package com.example.maciek.tumblrbrowser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(ResultPresenter.class)
public class ResultActivity extends NucleusAppCompatActivity<ResultPresenter> {

    public static final String NAME_SEARCH = "name_search";
    ResultAdapter adapter;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra(NAME_SEARCH);

        adapter = new ResultAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        getPresenter().getDataAsync(name);
    }

    public static Intent createIntent(Context context, String name) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(NAME_SEARCH, name);
        return intent;
    }

    public void setPostDetailOnMainThread(PostDetail postDetail) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setPostList(Arrays.asList(postDetail));
            }
        });
    }
}