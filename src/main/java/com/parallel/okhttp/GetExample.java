package com.parallel.okhttp;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import okhttp3.*;

import javax.swing.text.html.parser.Entity;

public class GetExample {
    final OkHttpClient client = new OkHttpClient();

    String getRun(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public void postRun(String url, String Json, okhttp3.Callback callback) {
        final MediaType jJSON = MediaType.parse("application/json; charset=utf-8");
        String json = Json;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(jJSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
//        String response = example.getRun("https://api.opensea.io/api/v1/collection/doodles-official");
//        System.out.println(response);
//        System.out.println(JSONPath.eval(response, "['collection']['stats']['floor_price']"));
//        System.out.println(JSONPath.eval(response, "$"));
//        System.out.println(JSONPath.eval(response, "$.collection.stats"));
//        System.out.println(JSONPath.eval(response, "$.collection.stats.floor_price"));
//        System.out.println(example.bodyFilter(response));

        String url = "http://172.23.29.168:8080/inner/v1/dataFeature/featureSearch/search";
        String json="{\n" +
                "    \"featureCodeList\": [\n" +
                "      \"offLine_p2pOutCoinCnt7d\",\n" +
                "      \"offLine_cardInCnt7d\",\n" +
                "      \"offLine_p2pInCoinCnt7d\",\n" +
                "      \"offLine_cntrTradeFeeAmt30d\"\n" +
                "    ],\n" +
                "    \"featureParams\": {\n" +
                "        \"deviceId\":\"eb179bf559354aa8e6e9209ffd48e890\",\n" +
                "        \"userId\": \"7940904480\",\n" +
                "        \"cardId\":\"src_ywflh2ebzqherd3ugadatk2kx4\"\n" +
                "    }\n" +
                "}";
        example.postRun(url,json, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("MainActivity 解析异常");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    String bodyFilter(String responseBody) {
        JSONObject jj = (JSONObject) JSON.parse(responseBody);

        return jj.get("$['collection']['editors']").toString();
    }
}