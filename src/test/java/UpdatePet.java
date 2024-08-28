import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdatePet {
    public static void main(String[] args) {
        String url = "https://petstore.swagger.io/v2/pet";

        Map<String, Object> header = new HashMap<>();

        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json");

        String requestBody = "{\n" +
                "  \"id\": 4,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"deneme\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        String contentType = ContentType.JSON.toString();
        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(header)
                .body(requestBody)
                .when().log().all()
                .put(url);
        response.then().log().all();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().jsonPath().getString(" name")).isEqualTo("deneme");
    }
}
