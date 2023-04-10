package com.parallel.leverage.api.oracle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.parallel.leverage.entity.InputData;
import com.parallel.leverage.object.BasicApi;

import java.io.IOException;

public class NftPrice extends BasicApi{
    private String dev_URL = "https://api-bff.nftpricefloor.com/nfts";
    private static final String FILTER ="bored-ape-yacht-club|mutant-ape-yacht-club|doodles|cryptopunks|otherdeed|||||";

    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public void getBody(InputData inputData) {
        try {
           JSONArray jsonArray = JSONArray.parseArray(getAPI(dev_URL));
           jsonArray.forEach(j->{
               if(!FILTER.contains(JSONPath.eval(j,"slug").toString())){
                   return;
               }else {
                   tablePrint(bodyFilter2(j.toString(),"oracle"));
               }
           });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NftPrice nftPrice = new NftPrice();
        nftPrice.getBody(new InputData());
    }
}
