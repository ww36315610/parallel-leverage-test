package bitget;

import com.parallel.leverage.object.BasicApi;

import java.io.IOException;

public class EventCheck extends BasicApi {
    private String dev_URL = "http://172.23.62.85:8080/inner/v1/dataRisk/EventCheckController/eventCheck";
    private String line_URL = "https://api.opensea.io/api/v1/asset/${address}/${token_id}/?include_orders=false";
    private String json = "{\n" +
            "    \"bizId\": \"kdear000000000054\",\n" +
            "    \"eventCode\": \"UserLogin\",\n" +
            "    \"params\": {\n" +
            "        \"appVersion\": \"1.4.3\",\n" +
            "        \"createTime\": \"2023-02-11 20:35:18\",\n" +
            "        \"countryCode\": \"US26\",\n" +
            "        \"ip\": \"164.52.51.91\",\n" +
            "        \"mobile\": \"$5$upexuser$MMkdmpO6ynVaEFqP0EYi5.jSezoMhHiba9CGnj47z84\",\n" +
            "        \"eventTime\": \"2023-02-11 20:35:19\",\n" +
            "        \"retCode\": 0,\n" +
            "        \"deviceId\": \"6f090220d1ecc9f41c60e2f538c68020\",\n" +
            "        \"userId\": \"8975072698\",\n" +
            "        \"email\": \"$5$upexuser$MMkdmpO6ynVaEFqP0EYi5.jSezoMhHiba9CGnj47z84\",\n" +
            "        \"terminalType\": 2\n" +
            "    }\n" +
            "}";
    /**
     * 1.构造url
     * 2.发送请求
     * 3.处理response
     */
    public void getBody() throws IOException{
        System.out.println(postAPI(dev_URL, json));
    }

    public static void main(String[] args)  {
        EventCheck ec = new EventCheck();
        try {
            ec.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
