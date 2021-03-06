package com.brodowski.maciek.tumblrbrowser;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(ResultPresenter.class)
public class ResultActivity extends NucleusAppCompatActivity<ResultPresenter> implements OnItemClickActionListener {

    private static final String SAVE_LIST = "save_list";
    public static final String NAME_SEARCH = "name_search";
    private static final String TYPE_SEARCH = "type_search";
    ResultAdapter adapter;
    TumblrPost post;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.no_posts_to_display)
    ImageView noPostImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra(NAME_SEARCH);
        String type = getIntent().getStringExtra(TYPE_SEARCH);

        adapter = new ResultAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickActionListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);

        dataLoading(name, type);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataLoading(name, type);

                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void dataLoading(String name, String type) {
        getPresenter().getDataAsync(name, type);

//            viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(swipeRefreshLayout));


    }

    public static Intent createIntent(Context context, String name, String type) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(NAME_SEARCH, name);
        intent.putExtra(TYPE_SEARCH, type);
        return intent;
    }

    public void setPostDetailOnMainThread(TumblrPost post) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setPostDetailsList(post.getPosts());
            }
        });
    }

    @Override
    public void onItemClick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SAVE_LIST, new ArrayList<>(adapter.getPostDetailsList()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        List<PostDetails> parcelableArrayList = savedInstanceState.getParcelableArrayList(SAVE_LIST);
        adapter.setPostDetailsList(parcelableArrayList);
        super.onRestoreInstanceState(savedInstanceState);
    }


    public void setExceptionLayoutOnMainThread() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RelativeLayout exceptionLayout = (RelativeLayout) findViewById(R.id.exception_layout);
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(exceptionLayout));
            }
        });
    }

    public void setNoPostImageOnMainThread() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(noPostImageView));
            }
        });

    }

    public void setSuccessLayoutOnMainThread() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(swipeRefreshLayout));
            }
        });
    }
}