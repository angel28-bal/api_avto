package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Класс для конфигурации тестового окружения.
 * Содержит настройки для работы с REST API PetStore.
 * Настраивает базовые параметры запросов, логирование и интеграцию с Allure.
 */
public class TestConfig {
    // Базовый URL API PetStore
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    
    // API ключ для аутентификации
    private static final String API_KEY = "special-key";
    
    // Спецификация запросов
    private static RequestSpecification spec;

    /**
     * Инициализирует конфигурацию тестового окружения.
     * Настраивает:
     * - Базовый URL
     * - Content-Type
     * - API ключ
     * - Логирование запросов и ответов
     * - Интеграцию с Allure
     */
    public static void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("api_key", API_KEY)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.requestSpecification = spec;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Возвращает спецификацию запросов.
     * Спецификация содержит базовые настройки для всех HTTP запросов.
     * 
     * @return RequestSpecification объект с настройками запросов
     */
    public static RequestSpecification getSpec() {
        return spec;
    }
}