package com.brodowski.maciek.tumblrbrowser;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.text_input_edit_text)
    TextInputEditText searchEditText;

    @BindView(R.id.search_image_button)
    ImageButton imageButton;

    @BindView(R.id.studious)
    Button button;

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.checkbox_radio_group)
    CheckBox checkBox;


    private Map<Integer, String> apiKeysMap = new HashMap<Integer, String>() {{
        put(R.id.text_radio_button, "?type=text");
        put(R.id.photo_radio_button, "?type=photo");
        put(R.id.video_radio_button, "?type=video");
        put(R.id.audio_radio_button, "?type=audio");
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.setText("studiousmedic");
            }
        });


    }

    @OnClick(R.id.search_image_button)
    public void searchPosts() {

        int checkRadioId = radioGroup.getCheckedRadioButtonId();
        String type = checkBox.isChecked() ? apiKeysMap.get(checkRadioId) : null;

        startActivity(ResultActivity.createIntent(SearchActivity.this,
                searchEditText.getText().toString(), type));
    }

    @OnCheckedChanged(R.id.checkbox_radio_group)
    void onTypeCheckboxStateChanged(CompoundButton buttonView, boolean isChecked) {
        radioGroup.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
    }

}
