package services.api;

import com.google.common.collect.Maps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import services.Work;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.registerParser;
import static org.hamcrest.Matchers.equalTo;

public class Department {
    public int parentDepartId;

    /**
     * 获取部门列表
     * @param access_token
     * @param id
     * @return
     */
    public Response list(int id){
       Response response =
        given()
                .param("access_token", Work.getInstance().getToken())
                .param("id", id)
        .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
        .then()
                .log().all()
                .body("errcode", equalTo(0))
        .extract().response();
       return response;
    }

    /**
     * 创建部门
     * @param access_token
     * @param name
     * @param parentDepartId
     * @return
     */
    public Response create(String name, int parentDepartId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", name);
        map.put("parentid", parentDepartId);

        Response response =
                given()
                        .queryParam("access_token", Work.getInstance().getToken()) //post 发送个别参数
                        .contentType(ContentType.JSON)
                        .body(map)
                .when()
                        .log().all()
                        .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                        .log().all()
                        .body("errcode", equalTo(0))
                .extract().response();
        return response;
    }

    /**
     * 删除部门
     * @param access_token
     * @param id
     * @return
     */
    public Response delete(int id){
        Response response =
        given()
                .param("access_token",Work.getInstance().getToken())
                .param("id",id)
        .when()
                .log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
        .then()
                .log().all()
                .body("errcode",equalTo(0))
        .extract().response();
        return response;
    }

    /**
     * 更新部门
     * @param access_token
     * @return
     */
    public Response update(){
        Response response =
        given()
                .queryParam("access_token",Work.getInstance().getToken())
        .when()
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
        .then()
                .log().all()
                .body("errcode",equalTo(0))
        .extract().response();
        return response;
    }
}
