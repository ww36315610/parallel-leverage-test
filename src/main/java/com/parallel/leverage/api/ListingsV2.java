package com.parallel.leverage.api;

import com.parallel.leverage.entity.InputData;
import com.parallel.leverage.object.BasicApi;

public class ListingsV2 extends BasicApi {
    String dev_URL = "https://api.opensea.io/v2/orders/ethereum/seaport/listings?asset_contract_address=${address}&limit=30&token_ids=${token_id}";
    String line_URL = "https://api.opensea.io/v2/orders/ethereum/seaport/listings?asset_contract_address=${address}&limit=30&token_ids=${token_id}";

    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public void getBody(InputData inputData) {
        super.getData(inputData, dev_URL, line_URL);
    }
}