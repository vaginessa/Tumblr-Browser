package com.example.maciek.tumblrbrowser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maciek on 2017-03-13.
 */
public class PostDetail {

    String id;
    @SerializedName("photo-url-400")
    String photoUrl;
    String date;
    String type;
    @SerializedName("photo-caption")
    String photoText;
    @SerializedName("regular-body")
    String regularText;


    public String getId() {
        return id;
    }

    public String getPhotoText() {
        return photoText;
    }

    public String getRegularText() { return regularText; }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
