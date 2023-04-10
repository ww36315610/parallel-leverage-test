package com.parallel.leverage.api;

import com.parallel.leverage.entity.InputData;
import com.parallel.leverage.object.AbstractActionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateListings extends AbstractActionHandler {
    String dev_URL = "https://api.opensea.io/api/v1/asset/${address}/${token_id}/listings?limit=20";
    String line_URL = "https://api.opensea.io/api/v1/asset/${address}/${token_id}/listings?limit=20";
    public static void main(String[] args) {}

    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public String getBody(InputData inputData) {
        String url = null;
        String response = null;
        Map<String, Object> map = new HashMap<>();
        try {
            String env = inputData.getEnv();
            String collectionId = inputData.getCollectionId();
            String contract = config.getString(collectionId + "." + env + ".contract");
            map.put("address", contract);
            map.put("token_id", inputData.getTokenId());
            if (env.equals("dev")) {
                url = dev_URL;
            } else {
                url = line_URL;
            }
            url = makeURL(url, map);
            response = getAPI(url);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bodyFilter(response, "listings");
    }
}