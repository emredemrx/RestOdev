import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPet {
    public static void main(String[] args) {
        String url = "https://petstore.swagger.io/v2/pet/4\n";
        Map<String, Object> header = new HashMap<>();
        header.put("accept", "application/json");
        String contentType = ContentType.JSON.toString();
        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(header)
                .when().log().all()
                .get(url);
        response.then().log().all();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().jsonPath().getString(" id")).isEqualTo("id");
        assertThat(response.body().jsonPath().getString("name")).isEqualTo("deneme");
    }
}
