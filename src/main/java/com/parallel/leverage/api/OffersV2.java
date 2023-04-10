package com.parallel.leverage.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.parallel.leverage.entity.InputData;
import com.parallel.leverage.object.AbstractActionHandler;
import com.parallel.leverage.object.BasicApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OffersV2 extends BasicApi {
    String dev_URL = "https://api.opensea.io/v2/orders/ethereum/seaport/offers?asset_contract_address=${address}&limit=30&token_ids=${token_id}";
    String line_URL = "https://api.opensea.io/v2/orders/ethereum/seaport/offers?asset_contract_address=${address}&limit=30&token_ids=${token_id}";

    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public void getBody(InputData inputData) {
        super.getData(inputData, dev_URL, line_URL);
    }
}