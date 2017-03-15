package com.example.maciek.tumblrbrowser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maciek on 2017-03-10.
 */
public class Post {

    private Tumblelog tumblelog;

    @SerializedName("posts-total")
    private int postsTotal;

    private List<PostDetail> posts;

    public Tumblelog getTumblelog() {
        return tumblelog;
    }

    public int getPostsTotal() {
        return postsTotal;
    }

    public List<PostDetail> getPosts() {
        return posts;
    }


}
