package auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import config.TestConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ApiClient;

/**
 * Тесты для проверки авторизации в API PetStore.
 * Проверяет работу API с разными вариантами API ключей:
 * - Валидный ключ "special-key"
 * - Отсутствующий ключ
 * - Неверный ключ
 */
@Epic("Pet Store API")
@Feature("Authorization")
public class AuthTest {

    /**
     * Инициализация тестового окружения.
     * Настраивает базовую конфигурацию для всех тестов.
     */
    @BeforeAll
    public static void init() {
        TestConfig.setup();
    }

    /**
     * Проверяет, что запрос с валидным API ключом "special-key"
     * успешно выполняется и возвращает статус 200.
     * Использует метод GET /store/inventory как тестовый эндпоинт.
     */
    @Test
    @Description("Проверка доступа с валидным API ключом")
    public void testValidApiKey() {
        Response response = ApiClient.get("/store/inventory");
        assertEquals(200, response.getStatusCode(), "Запрос с валидным API ключом должен возвращать 200");
    }

    /**
     * Проверяет, что запрос без API ключа возвращает ошибку авторизации.
     * Намеренно не использует ApiClient для отправки запроса без ключа.
     * Ожидается получение статуса 401 (Unauthorized).
     */
    @Test
    @Description("Проверка доступа без API ключа")
    public void testWithoutApiKey() {
        // Временно удаляем API ключ
        Response response = RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/store/inventory");
        
        assertEquals(401, response.getStatusCode(), "Запрос без API ключа должен возвращать 401");
    }

    /**
     * Проверяет, что запрос с неверным API ключом возвращает ошибку авторизации.
     * Использует произвольный неверный ключ "invalid-key".
     * Ожидается получение статуса 401 (Unauthorized).
     */
    @Test
    @Description("Проверка доступа с неверным API ключом")
    public void testInvalidApiKey() {
        Response response = RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .header("api_key", "invalid-key")
                .when()
                .get("/store/inventory");
        
        assertEquals(401, response.getStatusCode(), "Запрос с неверным API ключом должен возвращать 401");
    }
} 