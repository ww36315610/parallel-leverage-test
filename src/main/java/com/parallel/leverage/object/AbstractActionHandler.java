package com.parallel.leverage.object;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.parallel.leverage.utils.ConfigTools;
import com.parallel.leverage.utils.TextTableUtils;
import com.parallel.leverage.utils.UrlAppend;
import javafx.util.Pair;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public abstract class AbstractActionHandler extends ConfigTools implements ApiObject {
    private static final MediaType JSONMedia;
    private final static String apiKey;
    private OkHttpClient client = new OkHttpClient();

    static {
        apiKey = "e019f2b5e0df465fa00bb7ad645138af";
        JSONMedia = MediaType.get("application/json; charset=utf-8");
    }

    public String getAPI(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("X-API-KEY", apiKey)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            return "";
        }
    }

    public String postAPI(String url, String json) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parameters", JSONObject.parse(json));
        RequestBody body = RequestBody.create(JSONMedia, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-API-KEY", apiKey)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // todo：封装请求url
    public String makeURL(String url, Map<String, Object> map) {
        return UrlAppend.appedUrl(url, map);
    }

    // todo：response
    public String makeResponse(String body, String byKey) {
        if (StringUtils.isEmpty(body) || StringUtils.isBlank(body)) {
            return "";
        }
        if (StringUtils.isEmpty(byKey) || StringUtils.isBlank(byKey)) {
            return body;
        }
        return JSONPath.eval(body, byKey).toString();
    }

    //todo: 处理response 扁平化json
    public String bodyFilter(String responseBody, String method) {
        JSONObject jsonRep = getConfigRoot(config.getConfig(method).toString());
        //遍历key和value
        Iterator iterator = jsonRep.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = jsonRep.getString(key);
            JSONPath.eval(responseBody, value);
        }
        return "";
    }
    //todo: 处理response 扁平化json
    public Pair<String[], String[][]> bodyFilter1(String responseBody, String method) {
        JSONObject jsonRep = getConfigRoot(config.getConfig(method).toString());
        //遍历key和value
        Iterator iterator = jsonRep.keySet().iterator();
        StringBuffer keySting = new StringBuffer();
        StringBuffer valueSting = new StringBuffer();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String valueReq = jsonRep.getString(key);
            String value = JSONPath.eval(responseBody, valueReq).toString();
            keySting.append(key + "#");
            valueSting.append(value + "#");
        }
        String[] keys = keySting.toString().substring(0, keySting.lastIndexOf("#")).split("#");
        String[][] values = new String[3][];
        values[0] = valueSting.toString().substring(0, valueSting.lastIndexOf("#")).split("#");
        return new Pair<>(keys, values);
    }

    //todo: 处理response 扁平化json
    public Pair<String[], String[][]> bodyFilter2(String responseBody, String method) {
        JSONObject jsonRep = getConfigRoot(config.getConfig(method).toString());
        String[] keys = new String[]{"key", "data"};
        String[][] values = new String[jsonRep.keySet().size()][2];
        //遍历key和value
        Iterator iterator = jsonRep.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String valueReq = jsonRep.getString(key);
            try {
                String value = JSONPath.eval(responseBody, valueReq) == null ? "null" : JSONPath.eval(responseBody, valueReq).toString();
                values[i][0] = key;
                values[i][1] = value;
                i++;
            }catch (Exception e){
                values[i][0] = "null";
                values[i][1] = "null";
                i++;
            }
        }
        return new Pair<>(keys, values);
    }

    //输出 table格式
    public void tablePrint(Pair<String[], String[][]> pair) {
        String[] keys = pair.getKey();
        String[][] values = pair.getValue();
        TextTableUtils.tableOut(keys, values);
    }

}
