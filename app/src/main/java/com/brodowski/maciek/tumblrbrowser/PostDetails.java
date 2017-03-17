package com.brodowski.maciek.tumblrbrowser;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Maciek on 2017-03-13.
 */
public class PostDetails implements Parcelable {

    String id;
    @SerializedName("photo-url-400")
    String photoUrl;
    String url;
    String date;
    String type;
    @SerializedName("photo-caption")
    String photoText;
    @SerializedName("regular-body")
    String regularText;

    String slug;

    protected PostDetails(Parcel in) {
        id = in.readString();
        photoUrl = in.readString();
        url = in.readString();
        date = in.readString();
        type = in.readString();
        photoText = in.readString();
        regularText = in.readString();
        slug = in.readString();
    }

    public static final Creator<PostDetails> CREATOR = new Creator<PostDetails>() {
        @Override
        public PostDetails createFromParcel(Parcel in) {
            return new PostDetails(in);
        }

        @Override
        public PostDetails[] newArray(int size) {
            return new PostDetails[size];
        }
    };

    public String getSlug() {
        return slug;
    }

    public String getUrl() {
        return url;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(photoUrl);
        dest.writeString(url);
        dest.writeString(date);
        dest.writeString(type);
        dest.writeString(photoText);
        dest.writeString(regularText);
        dest.writeString(slug);
    }
}
