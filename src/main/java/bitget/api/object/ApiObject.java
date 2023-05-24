package bitget.api.object;


import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.Map;

public interface ApiObject {
    /**
     * GET：请求url 获取response
     *
     * @param url
     * @return
     * @throws IOException
     */
     String getAPI(String url) throws IOException;

    /**
     * POST：请求url 获取response
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
     String postAPI(String url, String json) throws IOException;

    /**
     * 构造请求URL
     *
     * @param url
     * @param map
     * @return
     */
     String makeURL(String url, Map<String, Object> map);

    /**
     * 处理response：整体返回
     *
     * @param body
     * @param byKey
     * @return
     */
     String makeResponse(String body, String byKey);

    /***
     * 处理response：根据表达式返回json对象
     * @param responseBody
     * @param method
     * @return
     */
     String bodyFilter(String responseBody, String method);

    /**
     * 处理response：根据表达式返回Pair对象
     *
     * @param responseBody
     * @param method
     * @return
     */
     Pair<String[], String[][]> bodyFilter2(String responseBody, String method);

    /**
     * 格式化打印：表格
     *
     * @param pair
     */
     void tablePrint(Pair<String[], String[][]> pair);
}
