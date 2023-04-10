package com.parallel.leverage.object;

import com.parallel.leverage.entity.InputData;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BasicApi extends AbstractActionHandler {

    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public void getData(InputData inputData, String dev_URL, String line_URL) {
        String url = null;
        String response = null;
        Map<String, Object> map = new HashMap<>();
        try {
            String env = inputData.getEnv();
            String collectionId = inputData.getCollectionId();
            String slug = config.getString(collectionId + "." + env + ".slug");
            String contract = config.getString(collectionId + "." + env + ".contract");
            map.put("address", contract);
            map.put("token_id", inputData.getTokenId());
            map.put("collection", slug);
            if (env.equals("dev")) {
                url = dev_URL;
            } else {
                url = line_URL;
            }
            url = makeURL(url, map);
            response = getAPI(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePrint(bodyFilter2(response, inputData.getMethod()));
    }
}
