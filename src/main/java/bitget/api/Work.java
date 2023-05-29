package services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Work {

    private static Work work;
    String token;

    public static Work getInstance() {
        if (work == null) {
            work = new Work();
        }
        return work;
    }

    public String getToken() {
        if(token == null) {
            token =
                    given()
                            .param("corpid", "wwe2c3250da5791212")
                            .param("corpsecret", "oE0Sn1Ol822V9jdK3Pzop9N-gFwsqiu2KHB8Oa6ng74")
                    .when()
                            .log().all()
                            .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then()
                            .log().all()
                            .body("errcode", equalTo(0))
                    .extract()
                            .body().path("access_token");
            System.out.println(token);
        }
        return token;
    }
}
