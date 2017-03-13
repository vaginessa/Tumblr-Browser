package com.example.maciek.tumblrbrowser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(ResultPresenter.class)
public class ResultActivity  extends NucleusAppCompatActivity<ResultPresenter> {

    public static final String TITLE_SEARCH = "titleSearch";

    @BindView(R.id.button_find)
    Button findButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String title = getIntent().getStringExtra(TITLE_SEARCH);
        ButterKnife.bind(this);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(ResultActivity.this, getPresenter().getDataAsync(title), Toast.LENGTH_SHORT).show();

//                    getPresenter().getData(title);
            }
        });

    }
//    @OnClick(R.id.find_button)
//    public void findB () throws IOException {
//        Toast.makeText(this, ""+ , Toast.LENGTH_SHORT).show();
//    }

    public static Intent createIntent (Context context, String title){
        Intent intent = new Intent (context, ResultActivity.class);
        intent.putExtra(TITLE_SEARCH, title);
        return intent;
    }

}