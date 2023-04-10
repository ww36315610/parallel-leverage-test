package com.parallel.leverage.utils;

import java.util.Map;

public class UrlAppend {

    public static String appedUrl(String url, Map<String, Object> map) {
        for (String k : map.keySet()) {
            if (url.contains(k)) {
                url = url.replace("${" + k + "}", map.get(k).toString());
            }
        }
        System.out.println(url);
        return url;
    }
}
