package com.parallel.leverage.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.parallel.leverage.object.AbstractActionHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class CreateOffers extends AbstractActionHandler {
    final OkHttpClient client = new OkHttpClient();
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String bodyFilter(String responseBody) {
        JSONObject jj = (JSONObject) JSON.parse(responseBody);

        return jj.get("$['collection']['editors']").toString();
    }
}