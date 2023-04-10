package com.parallel.leverage.api;

import com.parallel.leverage.entity.InputData;
import com.parallel.leverage.object.BasicApi;

public class Offers extends BasicApi {
    String dev_URL = "https://api.opensea.io/api/v1/asset/${address}/${token_id}/offers?limit=30";
    String line_URL = "https://api.opensea.io/api/v1/asset/${address}/${token_id}/offers?limit=30";

    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public void getBody(InputData inputData) {
        super.getData(inputData, dev_URL, line_URL);
    }
}