package com.example.maciek.tumblrbrowser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public static final String NAME_SEARCH = "titleSearch";

    @BindView(R.id.button_find)
    Button findButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String name = getIntent().getStringExtra(NAME_SEARCH);
        ButterKnife.bind(this);
        

    }

    public static Intent createIntent (Context context, String name){
        Intent intent = new Intent (context, ResultActivity.class);
        intent.putExtra(NAME_SEARCH, name);
        return intent;
    }

}