package com.parallel.okhttp;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.text.html.parser.Entity;

public class GetExample {
    final OkHttpClient client = new OkHttpClient();
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
        String response = example.run("https://api.opensea.io/api/v1/collection/doodles-official");
        System.out.println(response);
        System.out.println(JSONPath.eval(response, "['collection']['stats']['floor_price']"));
        System.out.println(JSONPath.eval(response, "$"));
        System.out.println("11");
        System.out.println(JSONPath.eval(response, "$.collection.stats"));
        System.out.println(JSONPath.eval(response, "$.collection.stats.floor_price"));
        System.out.println(example.bodyFilter(response));
    }

    String bodyFilter(String responseBody) {
        JSONObject jj = (JSONObject) JSON.parse(responseBody);

        return jj.get("$['collection']['editors']").toString();
    }
}