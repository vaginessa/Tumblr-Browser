package com.example.maciek.tumblrbrowser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    public static final String LOGIN_SEARCH = "loginSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String userName = getIntent().getStringExtra(LOGIN_SEARCH);

    }

    public static Intent createIntent (Context context, String userName){
        Intent intent = new Intent (context, ResultActivity.class);
        intent.putExtra(LOGIN_SEARCH, userName);
        return intent;
    }

}