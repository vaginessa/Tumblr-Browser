package com.example.maciek.tumblrbrowser;

import android.util.Log;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import nucleus.presenter.Presenter;

/**
 * Created by Maciek on 2017-03-13.
 */

public class ResultPresenter extends Presenter<ResultActivity> {


    private String resultRaw;
    private String result;


    public void getDataAsync(String name, String type) {
        new Thread() {
            @Override
            public void run() {
                try {
                    JsonElement userData = fetchUserData(name, type);
//                    JsonArray userPosts = getPostsFromJSON(userData);
//                    JsonObject post1 = userPosts.get(0).getAsJsonObject();
                    Gson gson = new Gson();
                    Post post = gson.fromJson(userData.toString(), Post.class);
                    getView().setPostDetailOnMainThread(post);



                } catch (IOException e) {
                    e.printStackTrace();


                }

            }
        }.start();

    }


//    public String getData(String name) throws IOException {
//        String stringUrl = "https://" + name + ".tumblr.com/api/read/json/";
//        URL url = new URL(stringUrl);
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        InputStream inputStream = urlConnection.getInputStream();
//        return convertStreamToString(inputStream);
//    }
//
//    private String convertStreamToString(java.io.InputStream is) {
//        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
//        return s.hasNext() ? s.next() : "";
//    }


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
