package com.tommykvant.wikivoyage.fetchers;

import java.io.IOException;
import java.net.URI;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebFetcher {


    public static String fetch(URI url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url.toString())
                .headers(new Headers.Builder().add("Content-Type", "application/json; charset=utf-8").build())
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
//        DefaultHttpClient client = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(url);
//        httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
//        HttpResponse execute = client.execute(httpGet);
//        HttpEntity entity = execute.getEntity();
//        return EntityUtils.toString(entity, HTTP.UTF_8);
    }
}
