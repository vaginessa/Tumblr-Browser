package com.example.maciek.tumblrbrowser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maciek on 2017-03-10.
 */
public class Post {

    private Tumblelog tumblelog;

    @SerializedName("posts-start")
    private int postStart;

    @SerializedName("posts-total")
    private int postsTotal;

    @SerializedName("posts-type")
    boolean postsType;

    private List<PostDetail> posts;

    public Tumblelog getTumblelog() {
        return tumblelog;
    }

    public int getPostStart() {
        return postStart;
    }

    public int getPostsTotal() {
        return postsTotal;
    }

    public boolean isPostsType() {
        return postsType;
    }

    public List<PostDetail> getPosts() {
        return posts;
    }

//    {
//        "tumblelog":{ obiekt },
//        "posts-start":0,
//            "posts-total":3791,
//            "posts-type":false,
//            "posts":[  ]
//    };
}
