package com.parallel.leverage.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class ConfigTools {
    public static Config config;
    private static final String FILE = "opensea.conf";

    // 获取到conf 文件
    static {
        String path = ConfigTools.class.getClassLoader()
                .getResource(FILE).getPath();

        // liunx服务器作为-Dconf='conf/app.properties'读取服务器文件
//        String path = System.getProperty("conf");
        Config parsedConfig = ConfigFactory.parseFile(new File(path));
        config = ConfigFactory.load(parsedConfig);
    }

    public JSONObject getConfigRoot(String configRoot) {
        String simple = configRoot.split("SimpleConfigObject\\(")[1].toString();
        JSONObject jsonObject = JSON.parseObject(simple.substring(0, simple.indexOf(")")));
        return jsonObject;
    }

    public static void main(String[] args) {
        System.out.println(config.getConfig("API"));
        ConfigTools ct = new ConfigTools();
        ct.getConfigRoot(config.getConfig("asset").toString());
    }
}
