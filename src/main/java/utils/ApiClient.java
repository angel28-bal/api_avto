package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// Класс для работы с API PetStore
public class ApiClient {

    // Базовый URL API
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    // Создает базовую спецификацию запроса
    private static RequestSpecification getBaseSpec() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("api_key", "special-key")
                .contentType("application/json");
    }

    // Отправляет GET запрос
    public static Response get(String endpoint) {
        return getBaseSpec()
                .when()
                .get(endpoint);
    }

    // Отправляет POST запрос
    public static Response post(String endpoint, Object body) {
        return getBaseSpec()
                .body(body)
                .when()
                .post(endpoint);
    }

    // Отправляет PUT запрос
    public static Response put(String endpoint, Object body) {
        return getBaseSpec()
                .body(body)
                .when()
                .put(endpoint);
    }

    // Отправляет DELETE запрос
    public static Response delete(String endpoint) {
        return getBaseSpec()
                .when()
                .delete(endpoint);
    }
}
