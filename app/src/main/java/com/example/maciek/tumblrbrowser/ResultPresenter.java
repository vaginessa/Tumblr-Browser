package com.example.maciek.tumblrbrowser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import nucleus.presenter.Presenter;

/**
 * Created by Maciek on 2017-03-13.
 */

public class ResultPresenter extends Presenter<ResultActivity> {


    private String result;

    public String getDataAsync (String title) {
        new Thread(){
            @Override
            public void run() {
                try {
                     result =  getData(title).substring(22);


                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        }.start();

        return result;
    }


    //    public void getDataAnsync(String title){        //osobny watek do przetwarzania danych
//        new Thread(){                               //requesty sieciowe nie moga byc odpalane na wątku głónym!!!!
//            @Override
//            public void run() {
//                try {
//                    String result = getData(title);
//                    SearchResult searchResult = new Gson().fromJson(result,SearchResult.class); //metoda ktora przetwarza json na model przez nas stwotrzony
//                    getView().setDataOnUiThread(searchResult, false);
//                } catch (IOException e) {
//                    getView().setDataOnUiThread(null, true);
//                }
//            }
//        }.start();
//
//    }




    public String getData (String title) throws IOException {
        String stringUrl = "https://" + title + ".tumblr.com/api/read/json";
        URL url = new URL(stringUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        return convertStreamToString(inputStream);
    }

        private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


}
