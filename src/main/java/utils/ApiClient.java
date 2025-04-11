package utils;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// Класс для работы с API PetStore
public class ApiClient {

    // Создает базовую спецификацию запроса
    private static RequestSpecification getBaseSpec() {
        if (RestAssured.requestSpecification == null) {
            TestConfig.setup();
        }
        return RestAssured.given();
    }

    // Отправляет GET запрос
    public static Response get(String endpoint) {
        return getBaseSpec()
                .when()
                .get(endpoint);
    }

    // Отправляет POST запрос
    @SuppressWarnings("UseSpecificCatch")
    public static Response post(String endpoint, Object body) {
        try {
            RequestSpecification spec = getBaseSpec();
            if (spec == null) {
                throw new IllegalStateException("Request specification is null");
            }
            return spec
                    .body(body)
                    .when()
                    .post(endpoint);
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute POST request: " + e.getMessage(), e);
        }
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
