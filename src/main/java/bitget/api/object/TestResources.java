package bitget.api.object;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TestResources {
    public String name;
    public Map<String, String> featureParams;
    public int age;


    public static void main(String[] args) {
        TestResources tr = new TestResources();
        HashMap<String, Object> mapTemp = Maps.newHashMap();
        mapTemp.put("userId", "123123123");
        String path = "/bitget/api/feature/search.json";
        try {
            String body = tr.template(path, mapTemp);
            System.out.println(body);
            tr.readJsonReturnJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void readFile() {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("/app/testcase/TestStock.yaml"));
        System.out.println("----");
//            System.out.println(FileUtils.readFileToString(new File(this.getClass().getResource("/app/testcase/TestStock.yaml").getPath())));
        System.out.println("----");
    }

    /**
     * 写json文件
     */
    public void writeJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("demo.json"), this);
    }

    /**
     * 读取json文件
     * 返回此类的一个对象
     */
    public void readJsonReturnClass() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestResources demo = mapper.readValue(new File(this.getClass().getResource("/bitget/api/feature/search.json").getPath()), this.getClass());
        System.out.println(demo);
        System.out.println(demo.name);
        System.out.println(demo.age);
    }

    /**
     * 读取json文件
     * 返回JSONObject对象
     */
    public void readJsonReturnJsonObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //        String jsonObject = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        JSONObject jsonObject = mapper.readValue(new File(this.getClass().getResource("/bitget/api/feature/search.json").getPath()), JSONObject.class);
        System.out.println(jsonObject);

    }

    /**
     * 模版化技术-返回jsonString
     *
     * @return
     */
    public String template(String templatePath, HashMap<String, Object> data) {
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(this.getClass().getResource(templatePath).getPath());
        mustache.execute(writer, data);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    /**
     * 读取yaml文件
     * 返回此类的一个对象
     */
    public void readYamlObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestResources demo = mapper.readValue(this.getClass().getResourceAsStream("/app/testcase/TestStock.yaml"), this.getClass());
        System.out.println(demo);
        System.out.println(demo.name);
        System.out.println(demo.age);
        System.out.println(demo.featureParams.get("userId"));
        String k = demo.featureParams.toString();
        System.out.println(k);
    }

    /**
     * 读取yaml文件
     * 返回object[][]二维数组
     */
    public void readYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object[][] demo = mapper.readValue(this.getClass().getResourceAsStream("/app/testcase/TestStock.yaml"), Object[][].class);
        System.out.println(demo);
        System.out.println(demo.length);
        System.out.println(demo[0].length);
        System.out.println(demo[0]);
        System.out.println(demo[0][0]);
    }
}
