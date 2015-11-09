package com.tommykvant.wikivoyage.fetchers;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class WebFetcher {

    public static String fetch(URI url) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
        HttpResponse execute = client.execute(httpGet);
        HttpEntity entity = execute.getEntity();
        return EntityUtils.toString(entity, HTTP.UTF_8);
    }
}
