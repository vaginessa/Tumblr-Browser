package com.brodowski.maciek.tumblrbrowser;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import nucleus.presenter.Presenter;

/**
 * Created by Maciek on 2017-03-13.
 */

public class ResultPresenter extends Presenter<ResultActivity> {


    private String resultRaw;
    private String result;
    private TumblrPost tumblrPost;
    private boolean isEmpty = false;

    public boolean isEmpty() {
        return isEmpty;
    }

    public TumblrPost getTumblrPost() {
        return tumblrPost;
    }

    public void getDataAsync(String name, String type) {
        new Thread() {
            @Override
            public void run() {
                RelativeLayout exceptionLayout = null;
                try {
                    JsonElement userData = fetchUserData(name, type);
//                    JsonArray userPosts = getPostsFromJSON(userData);
//                    JsonObject post1 = userPosts.get(0).getAsJsonObject();
                    Gson gson = new Gson();
                    tumblrPost = gson.fromJson(userData.toString(), TumblrPost.class);



//                    if (userPosts.size()==0){
//                        isEmpty=true;
//                    } else {
//                        isEmpty=false;
//                    }

                    if (tumblrPost != null) {
                        getView().setPostDetailOnMainThread(tumblrPost);
                    }

                    if (tumblrPost.getPostsTotal()==0){
                        getView().setNoPostImageOnMainThread();
                    } else {
                        getView().setSuccessLayoutOnMainThread();
                    }




                } catch (Exception e) {
                    e.printStackTrace();

                    if (getView()!= null){
                    exceptionLayout = (RelativeLayout) getView().findViewById(R.id.exception_layout);}
                    if (exceptionLayout != null) {
                        getView().setExceptionLayoutOnMainThread();
//                        getView().viewFlipper.setDisplayedChild(getView().viewFlipper.indexOfChild(exceptionLayout));
                    }
                }

            }
        }.start();

    }

    public static JsonElement fetchUserData(String userName, String type) throws IOException {
        if (type == null) {
            type = "";
        }
        String stringUrl = "https://" + userName + ".tumblr.com/api/read/json/" + type;
        InputStream inputStream = getInputStream(stringUrl);
        String jsonString = convertStreamToString(inputStream);
        jsonString = jsonString.substring(22, jsonString.length() - 2);
        return new JsonParser().parse(jsonString);
    }

    public static JsonArray getPostsFromJSON(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        return jsonObject.getAsJsonArray("posts");
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static InputStream getInputStream(String link) {
        try {
            URL url = new URL(link);
            return url.openConnection().getInputStream();
        } catch (IOException e) {

            return null;
        }
    }


}
