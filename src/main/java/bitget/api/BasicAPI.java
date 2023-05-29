package services.api;

import app.page.BasicPage;
import app.page.PageObjectModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class BasicAPI {
    APIObjectModel model;

    /**
     * 封装统一获取配置文件方法
     * 可以不需要每次调用都传入方法名
     */
    public void parseSetps() {
        //获取调用执行方法
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("===当前执行方法====" + method);
    }

    /**
     * 根据方法名获取配置文件方法
     * @param method
     */
    public void parseSetps(String method){
        //this.getClass() 那个类调用，就获类路径对应的yaml文件
        String yamlPath = "/" + this.getClass().getCanonicalName().replace('.', '/') + ".yaml";
        parseSteps(yamlPath,method);
    }

    /**
     * 生成统一配置yaml模版
     * 必须继承BasicPage类
     *
     * @param method
     * @param path
     */
    public void parseSteps(String path, String method) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            model = mapper.readValue(BasicPage.class.getResourceAsStream(path), APIObjectModel.class);
            parseSteps(model.methods.get(method));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  parseSteps(APIObjectMethod method){


    }

    /**
     * 测试方法
     */
    public void mm() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            System.out.println(element.getMethodName());
        }
    }
}
