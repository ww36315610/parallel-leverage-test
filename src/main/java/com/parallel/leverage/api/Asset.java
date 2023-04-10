package com.parallel.leverage.api;

import com.parallel.leverage.entity.InputData;
import com.parallel.leverage.object.BasicApi;

public class Asset extends BasicApi {
    private String dev_URL = "https://api.opensea.io/api/v1/asset/${address}/${token_id}/?include_orders=false";
    private String line_URL = "https://api.opensea.io/api/v1/asset/${address}/${token_id}/?include_orders=false";

    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public void getBody(InputData inputData) {
        super.getData(inputData, dev_URL, line_URL);
    }
}