#!/bin/bash
cat > src/main/java/utils/ApiClient.java << 'EOL'
package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    private static RequestSpecification getBaseSpec() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json");
    }

    public static Response get(String endpoint) {
        return getBaseSpec()
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return getBaseSpec()
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return getBaseSpec()
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return getBaseSpec()
                .when()
                .delete(endpoint);
    }
}EOL
