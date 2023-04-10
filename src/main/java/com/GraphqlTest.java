//package com;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.mountcloud.graphql.request.GraphqlRequestType;
//import org.mountcloud.graphql.request.mutation.GraphqlMutation;
//import org.mountcloud.graphql.request.query.GraphqlQuery;
//import org.mountcloud.graphql.response.DefaultGraphqlResponse;
//import org.mountcloud.graphql.response.GraphqlResponse;
//import org.mountcloud.graphql.util.HttpClientUtil;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class GraphqlTest {
//
//    public static void main(String[] args) {
//        //graphql服务器地址
//        String serverUrl = "http://localhost:8080/graphql";
//        //build一个新的graphqlclient
//        GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);
//
//        //如果说graphql需要健全信息我们可以用map来进行传递
//        Map<String, String> httpHeaders = new HashMap<>();
//        httpHeaders.put("token", "graphqltesttoken");
//        //设置http请求的头
//        graphqlClient.setHttpHeaders(httpHeaders);
//        //创建一个Query并设置query的名字为findUser
//        //如果有特殊需要可以自己继承GraphqlQuery,DefaultGraphqlQuery已经可以满足所有请求了
//        GraphqlQuery query = new DefaultGraphqlQuery("findUser");
//
//        //我们需要查询user的名字，性别还有年龄，设置需要查询的这三个属性。
//        query.addResultAttributes("name", "sex", "age");
//
//        try {
//            //执行query
//            GraphqlResponse response = graphqlClient.doQuery(query);
//            //获取数据，数据为map类型
//            Map result = response.getData();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
