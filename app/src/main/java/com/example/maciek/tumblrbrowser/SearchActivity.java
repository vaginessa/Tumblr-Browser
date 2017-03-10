package com.example.maciek.tumblrbrowser;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.text_input_edit_text)
    TextInputEditText searchEditText;

    @BindView(R.id.search_image_button)
    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.search_image_button)
    public void searchPosts () {
        startActivity(ResultActivity.createIntent(SearchActivity.this,
                searchEditText.getText().toString()));
    }

}
